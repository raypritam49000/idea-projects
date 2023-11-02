package com.addy.rest.app.repository;

import com.addy.rest.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndDeletedFalse(String username);
    Optional<User> findByUsernameOrEmailAndDeletedFalse(String username, String email);
    Optional<User> findByIdAndDeletedFalse(String id);

    Optional<User> findByEmailAndDeletedFalse(String email);
}
