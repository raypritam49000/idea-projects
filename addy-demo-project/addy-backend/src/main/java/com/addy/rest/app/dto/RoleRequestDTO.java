package com.addy.rest.app.dto;

import java.util.Set;

public class RoleRequestDTO {
    private String name;
    private Set<String> permissions;

    public RoleRequestDTO() {
    }

    public RoleRequestDTO(String name, Set<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "RoleRequestDto{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
