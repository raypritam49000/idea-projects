package com.addy.rest.app.dto;



public class PermissionDTO extends BaseDTO{
    private String abbr;
    private String name;

    public PermissionDTO() {
    }

    public PermissionDTO(String abbr, String name) {
        this.abbr = abbr;
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "abbr='" + abbr + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", deleted=" + deleted +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
