package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.in;

import java.util.List;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;

public interface  GetAllUsersUseCase {

    List<User> getAllUsers();
    
}
