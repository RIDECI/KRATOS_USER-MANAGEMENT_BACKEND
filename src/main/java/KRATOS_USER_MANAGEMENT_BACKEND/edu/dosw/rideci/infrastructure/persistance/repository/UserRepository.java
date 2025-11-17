package KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.repository;

import KRATOS_USER_MANAGEMENT_BACKEND.edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, Long>{
    
}
