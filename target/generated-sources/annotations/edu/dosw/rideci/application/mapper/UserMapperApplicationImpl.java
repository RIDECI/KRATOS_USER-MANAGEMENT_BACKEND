package edu.dosw.rideci.application.mapper;

import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.infrastructure.controller.dto.Request.UserRequest;
import edu.dosw.rideci.infrastructure.controller.dto.Response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T23:10:13-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperApplicationImpl implements UserMapperApplication {

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.userId( user.getUserId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.drivingLicense( user.getDrivingLicense() );
        userResponse.soat( user.getSoat() );
        userResponse.insurance( user.getInsurance() );
        userResponse.carPlate( user.getCarPlate() );
        userResponse.createdAt( user.getCreatedAt() );
        userResponse.role( user.getRole() );
        userResponse.state( user.getState() );
        userResponse.profile( user.getProfile() );
        userResponse.vehicleType( user.getVehicleType() );

        return userResponse.build();
    }

    @Override
    public UserRequest toRequest(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequest.UserRequestBuilder userRequest = UserRequest.builder();

        userRequest.userId( user.getUserId() );
        userRequest.name( user.getName() );
        userRequest.email( user.getEmail() );
        userRequest.phoneNumber( user.getPhoneNumber() );
        userRequest.drivingLicense( user.getDrivingLicense() );
        userRequest.soat( user.getSoat() );
        userRequest.insurance( user.getInsurance() );
        userRequest.carPlate( user.getCarPlate() );
        userRequest.role( user.getRole() );
        userRequest.state( user.getState() );
        userRequest.profile( user.getProfile() );
        userRequest.vehicleType( user.getVehicleType() );

        return userRequest.build();
    }

    @Override
    public User toDomain(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userRequest.getUserId() );
        user.name( userRequest.getName() );
        user.email( userRequest.getEmail() );
        user.phoneNumber( userRequest.getPhoneNumber() );
        user.drivingLicense( userRequest.getDrivingLicense() );
        user.soat( userRequest.getSoat() );
        user.insurance( userRequest.getInsurance() );
        user.carPlate( userRequest.getCarPlate() );
        user.role( userRequest.getRole() );
        user.state( userRequest.getState() );
        user.profile( userRequest.getProfile() );
        user.vehicleType( userRequest.getVehicleType() );

        return user.build();
    }

    @Override
    public List<UserResponse> toResponseList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toResponse( user ) );
        }

        return list;
    }

    @Override
    public List<User> toDomainList(List<UserRequest> userRequests) {
        if ( userRequests == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userRequests.size() );
        for ( UserRequest userRequest : userRequests ) {
            list.add( toDomain( userRequest ) );
        }

        return list;
    }
}
