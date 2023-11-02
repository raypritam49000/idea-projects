package com.addy.rest.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "addy-otp-collection")
public class UserOtp {
    @Id
    private String id;
    private String userId;
    private String otp;
    private Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "UserOtp{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", otp='" + otp + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
