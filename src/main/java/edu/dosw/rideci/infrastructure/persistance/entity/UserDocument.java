package edu.dosw.rideci.infrastructure.persistance.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.dosw.rideci.domain.model.enums.AccountState;
import edu.dosw.rideci.domain.model.enums.Profile;
import edu.dosw.rideci.domain.model.enums.Role;
import edu.dosw.rideci.domain.model.enums.VehicleType;
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
