package edu.dosw.rideci.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.dosw.rideci.application.mapper.UserMapperApplication;
import edu.dosw.rideci.application.port.in.CreateUserUseCase;
import edu.dosw.rideci.application.port.in.DeleteUserUseCase;
import edu.dosw.rideci.application.port.in.GetAllUsersUseCase;
import edu.dosw.rideci.application.port.in.GetUserUseCase;
import edu.dosw.rideci.application.port.in.UpdateUserUseCase;
import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.Role;
import edu.dosw.rideci.infrastructure.controller.UserController;
import edu.dosw.rideci.infrastructure.controller.dto.Request.UserRequest;
import edu.dosw.rideci.infrastructure.controller.dto.Response.UserResponse;

@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserMapperApplication userMapperApplication;

    @MockitoBean
    private CreateUserUseCase createUserUseCase;

    @MockitoBean
    private UpdateUserUseCase updateUserUseCase;

    @MockitoBean
    private DeleteUserUseCase deleteUserUseCase;

    @MockitoBean
    private GetUserUseCase getUserUseCase;

    @MockitoBean
    private GetAllUsersUseCase getAllUsersUseCase;


    private UserRequest userRequest;
    private UserResponse userResponse;
    private User userDomain;

    LocalDateTime now = LocalDateTime.of(2024, 11, 23, 18, 30, 0);
    List<User> userList;
    List<UserResponse> userResponseList;

    @BeforeEach
    void setup() {

            userRequest = new UserRequest();
            userRequest.setUserId(1000100444L);
            userRequest.setName("Juan Sebastian Puentes Julio");
            userRequest.setEmail("juan.puentes@mail.escuelaing.edu.co");
            userRequest.setIdentificationType(IdentificationType.CC);
            userRequest.setIdentificationNumber("1011084070");
            userRequest.setPhoneNumber("3235207883");
            userRequest.setAddress("Calle 187A # 8 - 46");
            userRequest.setRole(Role.STUDENT);

            userResponse = new UserResponse();
            userResponse.setUserId(1000100444L);
            userResponse.setName("Juan Sebastian Puentes Julio");
            userResponse.setEmail("juan.puentes@mail.escuelaing.edu.co");
            userResponse.setIdentificationType(IdentificationType.CC);
            userResponse.setIdentificationNumber("1011084070");
            userResponse.setPhoneNumber("3235207883");
            userResponse.setAddress("Calle 187A # 8 - 46");
            userResponse.setRole(Role.STUDENT);
            userResponse.setState(AccountState.PENDING);
            userResponse.setCreatedAt(now);

            userDomain = new User();
            userDomain.setUserId(1000100444L);
            userDomain.setName("Juan Sebastian Puentes Julio");
            userDomain.setEmail("juan.puentes@mail.escuelaing.edu.co");
            userDomain.setIdentificationType(IdentificationType.CC);
            userDomain.setIdentificationNumber("1011084070");
            userDomain.setPhoneNumber("3235207883");
            userDomain.setAddress("Calle 187A # 8 - 46");
            userDomain.setRole(Role.STUDENT);

            userList = List.of(userDomain);
            userResponseList = List.of(userResponse);
    
    }

    @DisplayName("Should create a new user successfully")
    @Test
    void shouldCreateUser() throws Exception {
        when(createUserUseCase.createUser(any(User.class))).thenReturn(userDomain);
        when(userMapperApplication.toDomain(any(UserRequest.class))).thenReturn(userDomain);
        when(userMapperApplication.toResponse(any(User.class))).thenReturn(userResponse);

        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                        "userId": 1000100444,
                        "name": "Juan Sebastian Puentes Julio",
                        "email": "juan.puentes@mail.escuelaing.edu.co",
                        "identificationType": "CC",
                        "identificationNumber": "1011084070",
                        "phoneNumber": "3235207883",
                        "address": "Calle 187A # 8 - 46",
                        "role": "STUDENT"

                    }
                    """))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.userId").value("1000100444"))
            .andExpect(jsonPath("$.name").value("Juan Sebastian Puentes Julio"))
            .andExpect(jsonPath("$.email").value("juan.puentes@mail.escuelaing.edu.co"))
            .andExpect(jsonPath("$.identificationType").value("CC"))
            .andExpect(jsonPath("$.identificationNumber").value("1011084070"))
            .andExpect(jsonPath("$.phoneNumber").value("3235207883"))
            .andExpect(jsonPath("$.address").value("Calle 187A # 8 - 46"))
            .andExpect(jsonPath("$.role").value("STUDENT"))
            .andExpect(jsonPath("$.state").value("PENDING"))
            .andExpect(jsonPath("$.createdAt").value("2024-11-23T18:30:00"));
            
    }

    @DisplayName("Should update an existing user successfully")
    @Test
    void shouldUpdateUser() throws Exception {
        when(updateUserUseCase.updateUser((any(Long.class)), any(User.class))).thenReturn(userDomain);
        when(userMapperApplication.toDomain(any(UserRequest.class))).thenReturn(userDomain);
        when(userMapperApplication.toResponse(any(User.class))).thenReturn(userResponse);

        mockMvc.perform(put("/users/{id}", 1000100444L)
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                        "userId": 1000100444,
                        "name": "Juan Sebastian Puentes Julio",
                        "email": "juan.puentes@mail.escuelaing.edu.co",
                        "identificationType": "TI",
                        "identificationNumber": "1011084070",
                        "phoneNumber": "3235207883",
                        "address": "Calle 187A # 8 - 46",
                        "role": "STUDENT"
                    }
                    """))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.userId").value("1000100444"))
            .andExpect(jsonPath("$.name").value("Juan Sebastian Puentes Julio"))
            .andExpect(jsonPath("$.email").value("juan.puentes@mail.escuelaing.edu.co"))
            .andExpect(jsonPath("$.identificationType").value("CC"))
            .andExpect(jsonPath("$.identificationNumber").value("1011084070"))
            .andExpect(jsonPath("$.phoneNumber").value("3235207883"))
            .andExpect(jsonPath("$.address").value("Calle 187A # 8 - 46"))
            .andExpect(jsonPath("$.role").value("STUDENT"))
            .andExpect(jsonPath("$.state").value("PENDING"))
            .andExpect(jsonPath("$.createdAt").value("2024-11-23T18:30:00"));
            
            
    }

    @DisplayName("Delete user successfully")
    @Test
    void shouldDeleteUser() throws Exception {
        doNothing().when(deleteUserUseCase).deleteUserById(1000100444L);
        mockMvc.perform(delete("/users/{id}", 1000100444L))
            .andExpect(status().isNoContent());
            
        verify(deleteUserUseCase, times(1)).deleteUserById(1000100444L);
    }

    @DisplayName("Should get a user by ID successfully")
    @Test
    void shouldGetUserById() throws Exception {
        when(getUserUseCase.getUserById(1000100444L)).thenReturn(userDomain);
        when(userMapperApplication.toResponse(any(User.class))).thenReturn(userResponse);

        mockMvc.perform(get("/users/{id}", 1000100444L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.userId").value("1000100444"))
            .andExpect(jsonPath("$.name").value("Juan Sebastian Puentes Julio"))
            .andExpect(jsonPath("$.email").value("juan.puentes@mail.escuelaing.edu.co"))
            .andExpect(jsonPath("$.identificationType").value("CC"))
            .andExpect(jsonPath("$.identificationNumber").value("1011084070"))
            .andExpect(jsonPath("$.phoneNumber").value("3235207883"))
            .andExpect(jsonPath("$.address").value("Calle 187A # 8 - 46"))
            .andExpect(jsonPath("$.role").value("STUDENT"))
            .andExpect(jsonPath("$.state").value("PENDING"))
            .andExpect(jsonPath("$.createdAt").value("2024-11-23T18:30:00"));
    }

    @DisplayName("Should get all users successfully")
    @Test
    void shouldGetAllUsers() throws Exception {
        when(getAllUsersUseCase.getAllUsers()).thenReturn(userList);
        when(userMapperApplication.toResponseList(any())).thenReturn(userResponseList);
        mockMvc.perform(get("/users/allUsers")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].userId").value("1000100444"))
            .andExpect(jsonPath("$[0].name").value("Juan Sebastian Puentes Julio"))
            .andExpect(jsonPath("$[0].email").value("juan.puentes@mail.escuelaing.edu.co"))
            .andExpect(jsonPath("$[0].identificationType").value("CC"))
            .andExpect(jsonPath("$[0].identificationNumber").value("1011084070"))
            .andExpect(jsonPath("$[0].phoneNumber").value("3235207883"))
            .andExpect(jsonPath("$[0].address").value("Calle 187A # 8 - 46"))
            .andExpect(jsonPath("$[0].role").value("STUDENT"))
            .andExpect(jsonPath("$[0].state").value("PENDING"))
            .andExpect(jsonPath("$[0].createdAt").value("2024-11-23T18:30:00"));

    }
}