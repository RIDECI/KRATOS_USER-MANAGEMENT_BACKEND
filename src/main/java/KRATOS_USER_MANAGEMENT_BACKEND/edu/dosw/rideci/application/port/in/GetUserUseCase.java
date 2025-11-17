package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;

public interface GetUserUseCase {

    User getUserById(Long userId);
    
}
