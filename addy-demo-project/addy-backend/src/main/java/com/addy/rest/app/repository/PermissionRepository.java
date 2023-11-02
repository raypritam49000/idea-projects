package com.addy.rest.app.repository;

import com.addy.rest.app.model.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
    Permission findByName(String name);
    Permission findByNameAndAbbr(String name, String abbr);
}
