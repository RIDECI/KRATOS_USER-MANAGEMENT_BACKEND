package edu.dosw.rideci.infrastructure.persistance.repository.mapper;

import edu.dosw.rideci.domain.model.User;
import edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDocument toDocument(User user) {
        if ( user == null ) {
            return null;
        }

        UserDocument.UserDocumentBuilder userDocument = UserDocument.builder();

        userDocument.userId( user.getUserId() );
        userDocument.name( user.getName() );
        userDocument.email( user.getEmail() );
        userDocument.phoneNumber( user.getPhoneNumber() );
        userDocument.drivingLicense( user.getDrivingLicense() );
        userDocument.soat( user.getSoat() );
        userDocument.insurance( user.getInsurance() );
        userDocument.carPlate( user.getCarPlate() );
        userDocument.createdAt( user.getCreatedAt() );
        userDocument.role( user.getRole() );
        userDocument.state( user.getState() );
        userDocument.profile( user.getProfile() );
        userDocument.vehicleType( user.getVehicleType() );

        return userDocument.build();
    }

    @Override
    public User toDomain(UserDocument userDocument) {
        if ( userDocument == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userDocument.getUserId() );
        user.name( userDocument.getName() );
        user.email( userDocument.getEmail() );
        user.phoneNumber( userDocument.getPhoneNumber() );
        user.drivingLicense( userDocument.getDrivingLicense() );
        user.soat( userDocument.getSoat() );
        user.insurance( userDocument.getInsurance() );
        user.carPlate( userDocument.getCarPlate() );
        user.createdAt( userDocument.getCreatedAt() );
        user.role( userDocument.getRole() );
        user.state( userDocument.getState() );
        user.profile( userDocument.getProfile() );
        user.vehicleType( userDocument.getVehicleType() );

        return user.build();
    }

    @Override
    public List<User> toDomainList(List<UserDocument> userDocuments) {
        if ( userDocuments == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDocuments.size() );
        for ( UserDocument userDocument : userDocuments ) {
            list.add( toDomain( userDocument ) );
        }

        return list;
    }

    @Override
    public List<UserDocument> toDocumentList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDocument> list = new ArrayList<UserDocument>( users.size() );
        for ( User user : users ) {
            list.add( toDocument( user ) );
        }

        return list;
    }
}
