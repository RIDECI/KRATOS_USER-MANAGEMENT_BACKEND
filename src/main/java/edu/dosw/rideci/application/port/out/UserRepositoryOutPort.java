package edu.dosw.rideci.application.port.out;

import java.util.List;

import edu.dosw.rideci.domain.model.User;

public interface UserRepositoryOutPort {

    User save(User user);

    User findById(Long userId);

    void deleteById(Long userId);

    User updateUser(Long id, User user);

    List<User> getAllUsers();

}
