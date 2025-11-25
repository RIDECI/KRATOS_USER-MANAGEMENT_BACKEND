package edu.dosw.rideci.application.port.in;

import edu.dosw.rideci.domain.model.User;

public interface UpdateUserUseCase {

    User updateUser(Long id, User user);

}
