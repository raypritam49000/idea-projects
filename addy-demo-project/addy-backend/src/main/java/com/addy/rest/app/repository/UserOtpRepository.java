package com.addy.rest.app.repository;

import com.addy.rest.app.model.UserOtp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserOtpRepository extends MongoRepository<UserOtp,String> {
    UserOtp findByUserId(String userId);
}
