package edu.dosw.rideci.application.port.in;

import edu.dosw.rideci.domain.model.User;

public interface GetUserUseCase {

    User getUserById(Long userId);
    
}
