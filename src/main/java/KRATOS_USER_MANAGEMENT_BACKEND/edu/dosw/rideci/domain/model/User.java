package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model;


import java.time.LocalDateTime;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.AccountState;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.Profile;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.Role;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String drivingLicense;
    private String soat;
    private String insurance;
    private String carPlate;
    private LocalDateTime createdAt;

    private Role role;
    private AccountState state;
    private Profile profile;
    private VehicleType vehicleType;
}
