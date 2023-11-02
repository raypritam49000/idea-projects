package com.addy.rest.app.repository;

import com.addy.rest.app.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findFirstByName(String name);
    List<Role> findAllByNameInAndDeletedFalse(List<String> name);
    Role findByName(String name);
}
