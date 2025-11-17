package edu.dosw.rideci.infrastructure.persistance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;

public interface UserRepository extends MongoRepository<UserDocument, Long>{
    
}
