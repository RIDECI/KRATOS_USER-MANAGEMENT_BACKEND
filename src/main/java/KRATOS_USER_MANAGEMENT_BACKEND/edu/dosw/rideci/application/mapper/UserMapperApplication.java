package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.controller.dto.Request.UserRequest;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.controller.dto.Response.UserResponse;


@Mapper(componentModel = "spring")
public interface UserMapperApplication {

    UserResponse toResponse(User user);

    UserRequest toRequest(User user);

    User toDomain(UserRequest userRequest);

    List<UserResponse> toResponseList(List<User> users);

    List<User> toDomainList(List<UserRequest> userRequests);
    
    
}
