package edu.dosw.rideci.application.events.listener;

import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import edu.dosw.rideci.application.events.UserEvent;
import edu.dosw.rideci.application.events.UserSyncFailedEvent;
import edu.dosw.rideci.application.port.out.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserSyncErrorHandler implements RabbitListenerErrorHandler {

    private final EventPublisher eventPublisher;

    @Override
    public Object handleError(org.springframework.amqp.core.Message amqpMessage, Channel channel,
            Message<?> message, ListenerExecutionFailedException exception) {

        log.error("Retries exhausted for UserEvent. Publishing compensation event. Error: {}",
                exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage());

        if (message.getPayload() instanceof UserEvent) {
            UserEvent userEvent = (UserEvent) message.getPayload();
            UserSyncFailedEvent failedEvent = UserSyncFailedEvent.builder()
                    .userId(userEvent.getUserId())
                    .email(userEvent.getEmail())
                    .reason(exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage())
                    .build();

            // Publicar evento de compensación
            eventPublisher.publish(failedEvent, "user.sync.failed");

            log.info("Compensation event published for userId: {}", userEvent.getUserId());
        } else {
            log.warn("Could not cast payload to UserEvent. Payload type: {}",
                    message.getPayload().getClass().getName());
        }

        return null;
    }
}
