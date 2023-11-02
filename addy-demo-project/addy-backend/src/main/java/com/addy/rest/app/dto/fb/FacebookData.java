package com.addy.rest.app.dto.fb;

public class FacebookData {

    private String name;
    private String accessToken;
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FacebookData{" +
                "name='" + name + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}