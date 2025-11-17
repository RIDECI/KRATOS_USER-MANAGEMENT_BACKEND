package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.application.port.out.UserRepositoryOutPort;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.domain.model.User;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;
import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserRepositoryOutPort{
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {

        UserDocument userDocument = UserDocument.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .email(user.getEmail())
            .profile(user.getProfile())
            .role(user.getRole())
            .state(user.getState())
            .phoneNumber(user.getPhoneNumber())
            .vehicleType(user.getVehicleType())
            .drivingLicense(user.getDrivingLicense())
            .soat(user.getSoat())
            .insurance(user.getInsurance())
            .carPlate(user.getCarPlate())
            .createdAt(LocalDateTime.now())
            .build();

        UserDocument savedDocument = userRepository.save(userDocument);
        return userMapper.toDomain(savedDocument);
    }

    @Override
    public User updateUser(Long id, User user) {
        UserDocument actualUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        actualUser.setName(user.getName());
        actualUser.setEmail(user.getEmail());
        actualUser.setPhoneNumber(user.getPhoneNumber());
        actualUser.setDrivingLicense(user.getDrivingLicense());
        actualUser.setSoat(user.getSoat());
        actualUser.setInsurance(user.getInsurance());
        actualUser.setCarPlate(user.getCarPlate());
        actualUser.setRole(user.getRole());
        actualUser.setState(user.getState());
        actualUser.setProfile(user.getProfile());
        actualUser.setVehicleType(user.getVehicleType());

        UserDocument updatedDocument = userRepository.save(actualUser);
        return userMapper.toDomain(updatedDocument);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findById(Long userId) {
        UserDocument userDocument = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDomain(userDocument);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserDocument> userDocuments = userRepository.findAll();
        return userMapper.toDomainList(userDocuments);
    }

}