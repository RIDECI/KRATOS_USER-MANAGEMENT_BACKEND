package edu.dosw.rideci.infrastructure.persistance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import edu.dosw.rideci.infrastructure.persistance.entity.UserDocument;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByUserId(Long userId);

    void deleteByUserId(Long userId);

}
