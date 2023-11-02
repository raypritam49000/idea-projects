package com.mongodb.rest.api.repository;

import com.mongodb.rest.api.entities.StudentContacts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentContactRepository extends MongoRepository<StudentContacts,String> {
}
