package edu.dosw.rideci.infrastructure.controller.dto.Response;

import java.time.LocalDateTime;

import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;

    private Role role;
    private AccountState state;
    
}
