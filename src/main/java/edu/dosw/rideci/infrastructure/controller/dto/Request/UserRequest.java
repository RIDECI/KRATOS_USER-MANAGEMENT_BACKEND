package edu.dosw.rideci.infrastructure.controller.dto.Request;

import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;

    private Role role;
    private AccountState state;

}
