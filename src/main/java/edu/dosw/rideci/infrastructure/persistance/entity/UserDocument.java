package edu.dosw.rideci.infrastructure.persistance.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "users")
public class UserDocument {

    @Id
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;

    private Role role;
    private AccountState state;
    
}
