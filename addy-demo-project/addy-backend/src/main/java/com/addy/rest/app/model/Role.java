package com.addy.rest.app.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "addy-role-collection")
public class Role extends BaseEntity {
    private String name;

    @DBRef
    private Set<Permission> permissions;

    public Role() {
    }

    public Role(String name, Set<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                ", id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", deleted=" + deleted +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
