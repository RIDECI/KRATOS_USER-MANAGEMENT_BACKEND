package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in.CreateUserUseCase;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in.GetUserUseCase;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in.UpdateUserUseCase;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.out.UserRepositoryOutPort;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.repository.UserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in.DeleteUserUseCase;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in.GetAllUsersUseCase;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;

@RequiredArgsConstructor
@Service
public class UserService implements CreateUserUseCase, GetUserUseCase, UpdateUserUseCase, DeleteUserUseCase, GetAllUsersUseCase {
    
    private final UserRepositoryOutPort userRepositoryOutPort;

    @Override
    public User createUser(User user) {
        return userRepositoryOutPort.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepositoryOutPort.updateUser(id, user);

    }

    @Override
    public void deleteUserById(Long userId) {
        userRepositoryOutPort.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepositoryOutPort.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryOutPort.getAllUsers();
    }

}
