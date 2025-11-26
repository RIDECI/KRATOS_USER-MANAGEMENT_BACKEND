package edu.dosw.rideci.application.events.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.events.UserEvent;
import edu.dosw.rideci.application.port.in.CreateUserUseCase;
import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@RequiredArgsConstructor
@Component
@Slf4j
public class UserRegisteredListener {

    private final CreateUserUseCase createUserUseCase;

    @RabbitListener(queues = "user.sync.queue")
    public void handleUserRegistered(UserEvent event) {
        System.out.println("Received UserEvent: " + event);
        log.info("UserRegisteredListener handled event: {}", event);
        User eventUser = User.builder()
            .userId(event.getUserId())
            .name(event.getName())
            .email(event.getEmail())
            .identificationType(IdentificationType.valueOf(event.getIdentificationType()))
            .identificationNumber(event.getIdentificationNumber())
            .phoneNumber(event.getPhoneNumber())
            .address(event.getAddress())
            .role(Role.valueOf(event.getRole()))
            .build();
        createUserUseCase.createUser(eventUser);
    }
    
}
