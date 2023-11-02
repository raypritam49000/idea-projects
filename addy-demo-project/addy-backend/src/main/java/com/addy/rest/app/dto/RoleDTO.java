package com.addy.rest.app.dto;

import java.util.Date;
import java.util.Set;

public class RoleDTO extends BaseDTO {
    private String name;
    private Set<PermissionDTO> permissions;

    public RoleDTO() {
    }

    public RoleDTO(String id, Date creationDate, Date modificationDate, boolean deleted, String name) {
        super(id, creationDate, modificationDate, deleted);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
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
