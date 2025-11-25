package edu.dosw.rideci.application.port.in;

import java.util.List;

import edu.dosw.rideci.domain.model.User;

public interface  GetAllUsersUseCase {

    List<User> getAllUsers();
    
}
