package edu.dosw.rideci.infrastructure.persistance.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.dosw.rideci.application.events.UserEvent;
import edu.dosw.rideci.application.port.out.UserRepositoryOutPort;
import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;
import edu.dosw.rideci.infrastructure.persistance.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserRepositoryOutPort{
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RabbitEventPublisher eventPublisher;

    @Override
    public User save(User user) {

        UserDocument userDocument = UserDocument.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .email(user.getEmail())
            .identificationType(user.getIdentificationType())
            .identificationNumber(user.getIdentificationNumber())
            .address(user.getAddress())
            .role(user.getRole())
            .state(AccountState.PENDING)
            .phoneNumber(user.getPhoneNumber())
            .createdAt(LocalDateTime.now())
            .build();
        System.out.println("Saving user: " + userDocument);
        UserDocument savedDocument = userRepository.save(userDocument);

        UserEvent userCreatedEvent = userMapper.toUserEvent(savedDocument);

        eventPublisher.publish(userCreatedEvent, "user.created");
        
        return userMapper.toDomain(savedDocument);
    }

    @Override
    public User updateUser(Long id, User user) {
        UserDocument actualUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        actualUser.setName(user.getName());
        actualUser.setEmail(user.getEmail());
        actualUser.setIdentificationType(user.getIdentificationType());
        actualUser.setIdentificationNumber(user.getIdentificationNumber());
        actualUser.setAddress(user.getAddress());
        actualUser.setPhoneNumber(user.getPhoneNumber());
        actualUser.setRole(user.getRole());

        UserDocument updatedDocument = userRepository.save(actualUser);

        UserEvent userUpdatedEvent = userMapper.toUserEvent(updatedDocument);

        eventPublisher.publish(userUpdatedEvent, "user.updated");

        return userMapper.toDomain(updatedDocument);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);

        UserEvent userDeletedEvent = UserEvent.builder()
            .userId(userId)
            .build();

        eventPublisher.publish(userDeletedEvent,"user.deleted");
    }

    @Override
    public User findById(Long userId) {
        UserDocument userDocument = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDomain(userDocument);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserDocument> userDocuments = userRepository.findAll();
        return userMapper.toDomainList(userDocuments);
    }

}