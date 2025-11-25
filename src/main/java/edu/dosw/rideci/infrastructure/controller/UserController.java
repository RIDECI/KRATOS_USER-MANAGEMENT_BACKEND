package edu.dosw.rideci.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dosw.rideci.application.mapper.UserMapperApplication;
import edu.dosw.rideci.application.port.in.CreateUserUseCase;
import edu.dosw.rideci.application.port.in.DeleteUserUseCase;
import edu.dosw.rideci.application.port.in.GetAllUsersUseCase;
import edu.dosw.rideci.application.port.in.GetUserUseCase;
import edu.dosw.rideci.application.port.in.UpdateUserUseCase;
import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.infrastructure.controller.dto.Request.UserRequest;
import edu.dosw.rideci.infrastructure.controller.dto.Response.UserResponse;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final UserMapperApplication userMapper;

    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        
        User user = userMapper.toDomain(request);
        User createdUser = createUserUseCase.createUser(user);
        UserResponse response = userMapper.toResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        User user = userMapper.toDomain(request);
        User updatedUser = updateUserUseCase.updateUser(id, user);
        UserResponse response = userMapper.toResponse(updatedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        deleteUserUseCase.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = getUserUseCase.getUserById(id);
        UserResponse response = userMapper.toResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<User> users = getAllUsersUseCase.getAllUsers();
        List<UserResponse> responses = userMapper.toResponseList(users);
        
        return ResponseEntity.ok(responses);
    }
    

}
