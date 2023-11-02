package com.mongodb.rest.api.repository;

import com.mongodb.rest.api.entities.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address,String> {
}
