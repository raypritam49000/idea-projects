package com.addy.rest.app.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addy-permission-collection")
public class Permission extends BaseEntity {
    private String abbr;
    private String name;

    public Permission() {
    }

    public Permission(String abbr, String name) {
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
        return "Permission{" +
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
