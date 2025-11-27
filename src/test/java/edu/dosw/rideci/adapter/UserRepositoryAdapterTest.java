package edu.dosw.rideci.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.Role;
import edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;
import edu.dosw.rideci.infrastructure.persistance.repository.RabbitEventPublisher;
import edu.dosw.rideci.infrastructure.persistance.repository.UserRepository;
import edu.dosw.rideci.infrastructure.persistance.repository.UserRepositoryAdapter;
import edu.dosw.rideci.infrastructure.persistance.repository.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryAdapterTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private RabbitEventPublisher eventPublisher;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;

    private User user;
    private UserDocument savedUserDocument;
    private User updatedUser;
    private UserDocument updatedUserDocument;
    private List<UserDocument> userDocumentList;
    private List<User> userList;

    @BeforeEach
    void setUp() {

        LocalDateTime now = LocalDateTime.of(2025, 11, 26, 12, 0);
        user = User.builder()
            .userId(1000100444L)
            .name("Juan Sebastian Puentes Julio")
            .email("juan.puentes@mail.escuelaing.edu.co")
            .identificationType(IdentificationType.CC)
            .identificationNumber("1011084070")
            .phoneNumber("3235207883")
            .address("Calle 187A # 8-46")
            .role(Role.STUDENT)
            .createdAt(now)
            .phoneNumber("3235207883")
            .build();

        savedUserDocument = UserDocument.builder()
            .userId(1000100444L)
            .name("Juan Sebastian Puentes Julio")
            .email("juan.puentes@mail.escuelaing.edu.co")
            .identificationType(IdentificationType.CC)
            .identificationNumber("1011084070")
            .phoneNumber("3235207883")
            .address("Calle 187A # 8-46")
            .role(Role.STUDENT)
            .state(AccountState.PENDING)
            .createdAt(now)
            .build();

        updatedUser = User.builder()
            .userId(1000100444L)
            .name("Juan S. Puentes J.")
            .email("juan.puentes@mail.escuelaing.edu.co")
            .identificationType(IdentificationType.CC)
            .identificationNumber("1011084070")
            .phoneNumber("3235207883")
            .address("Calle 187A # 8-46")
            .role(Role.STUDENT)
            .createdAt(now)
            .phoneNumber("3235207883")
            .build();

        updatedUserDocument = UserDocument.builder()
            .userId(1000100444L)
            .name("Juan S. Puentes J.")
            .email("juan.puentes@mail.escuelaing.edu.co")
            .identificationType(IdentificationType.CC)
            .identificationNumber("1011084070")
            .phoneNumber("3235207883")
            .address("Calle 187A # 8-46")
            .role(Role.STUDENT)
            .state(AccountState.PENDING)
            .createdAt(now)
            .build();
        userDocumentList = List.of(savedUserDocument);
        userList = List.of(user);

    }

    @DisplayName("Save User - Success")
    @Test
    void shouldSaveUserSuccessfully() {
        when(userRepository.save(any(UserDocument.class))).thenReturn(savedUserDocument);
        when(userMapper.toDomain(any(UserDocument.class))).thenReturn(user);

        User result = userRepositoryAdapter.save(user);

        verify(eventPublisher, times(1)).publish(any(), eq("user.created"));
        assertEquals(user, result);
    }

    @DisplayName("Update User - Success")
    @Test
    void shouldUpdateUserSuccessfully() {
        when(userRepository.findById(1000100444L)).thenReturn(Optional.of(savedUserDocument));
        when(userRepository.save(any(UserDocument.class))).thenReturn(updatedUserDocument);
        when(userMapper.toDomain(any(UserDocument.class))).thenReturn(updatedUser);

        User result = userRepositoryAdapter.updateUser(1000100444L, updatedUser);
        verify(eventPublisher, times(1)).publish(any(), eq("user.updated"));
        assertEquals(updatedUser, result);
    }

    @DisplayName("Delete User - Success")
    @Test
    void shouldDeleteUserSuccessfully() {

        userRepositoryAdapter.deleteById(1000100444L);

        verify(userRepository, times(1)).deleteById(1000100444L);
        verify(eventPublisher, times(1)).publish(any(), eq("user.deleted"));
        
    }

    @DisplayName("Find User by ID - Success")
    @Test
    void shouldFindUserByIdSuccessfully() {
        when(userRepository.findById(1000100444L)).thenReturn(Optional.of(savedUserDocument));
        when(userMapper.toDomain(any(UserDocument.class))).thenReturn(user);
        User result = userRepositoryAdapter.findById(1000100444L);
        assertEquals(user, result);
    }

    @DisplayName("Find All Users - Success")
    @Test
    void shouldFindAllUsersSuccessfully() {
        when(userRepository.findAll()).thenReturn(userDocumentList);
        when(userMapper.toDomainList(anyList())).thenReturn(userList);
        List<User> result = userRepositoryAdapter.getAllUsers();
        assertEquals(userList, result);
    }
}