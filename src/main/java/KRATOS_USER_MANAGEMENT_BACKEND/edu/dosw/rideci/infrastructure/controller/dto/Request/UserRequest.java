package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.controller.dto.Request;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.AccountState;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.Profile;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.Role;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String drivingLicense;
    private String soat;
    private String insurance;
    private String carPlate;

    private Role role;
    private AccountState state;
    private Profile profile;
    private VehicleType vehicleType;

}
