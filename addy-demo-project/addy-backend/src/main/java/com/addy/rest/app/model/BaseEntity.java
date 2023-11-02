package com.addy.rest.app.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class BaseEntity {
    @Id
    protected String id;
    protected Date creationDate;
    protected Date modificationDate;
    protected boolean deleted;
    protected String createdBy;
    protected String modifiedBy;

    public BaseEntity() {
    }

    public BaseEntity(String id, Date creationDate, Date modificationDate, boolean deleted, String createdBy, String modifiedBy) {
        this.id = id;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", deleted=" + deleted +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
