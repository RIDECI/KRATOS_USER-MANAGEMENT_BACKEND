package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.repository.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDocument toDocument(User user);

    User toDomain(UserDocument userDocument);

    List<User> toDomainList(List<UserDocument> userDocuments);

    List<UserDocument> toDocumentList(List<User> users);
    
}
