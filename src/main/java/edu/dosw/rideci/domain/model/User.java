package edu.dosw.rideci.domain.model;

import java.time.LocalDateTime;

import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String id;
    private Long userId;
    private String name;
    private String email;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;

    private Role role;
    private AccountState state;
}
