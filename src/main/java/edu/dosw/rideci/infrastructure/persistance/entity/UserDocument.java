package edu.dosw.rideci.infrastructure.persistance.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "users")
public class UserDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private Long userId;
    private String name;
    @Indexed(unique = true)
    private String email;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;

    private Role role;
    private AccountState state;

}
