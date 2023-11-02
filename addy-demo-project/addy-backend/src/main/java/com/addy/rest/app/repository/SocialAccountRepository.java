package com.addy.rest.app.repository;

import com.addy.rest.app.model.SocialAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialAccountRepository extends MongoRepository<SocialAccount, String> {
    Optional<SocialAccount> findByEmail(String email);

    Optional<SocialAccount> findByProviderId(String providerId);
}
