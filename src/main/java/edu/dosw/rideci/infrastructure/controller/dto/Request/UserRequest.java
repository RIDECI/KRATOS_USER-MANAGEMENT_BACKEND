package edu.dosw.rideci.infrastructure.controller.dto.Request;

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
    private String recoveryEmail;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String phoneNumber;
    private String address;

    private Role role;

}
