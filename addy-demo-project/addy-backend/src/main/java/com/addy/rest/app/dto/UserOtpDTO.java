package com.addy.rest.app.dto;

import java.util.Date;

public class UserOtpDTO {
    private String id;
    private String userId;
    private String otp;
    private Date creationDate;

    public UserOtpDTO() {
    }

    public UserOtpDTO(String id, String userId, String otp, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.otp = otp;
        this.creationDate = creationDate;
    }

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
        return "UserOtpDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", otp='" + otp + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
