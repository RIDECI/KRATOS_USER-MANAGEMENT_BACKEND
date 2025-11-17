package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;

public interface UpdateUserUseCase {

    User updateUser(Long id, User user);

}
