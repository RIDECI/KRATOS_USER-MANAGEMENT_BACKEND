package edu.dosw.rideci.infrastructure.controller.dto.Request;

import java.time.LocalDateTime;

import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    
    private Long userId;
    private String name;
    private String email;
    private IdentificationType identificationType;
    private String identificationNumber;
    private LocalDateTime dateOfBirth;
    private String phoneNumber;
    private String address;

    private Role role;

}
