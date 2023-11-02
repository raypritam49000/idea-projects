package com.addy.rest.app.model;

import com.addy.rest.app.enumeration.AuthProvider;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "addy-social-account-collection")
public class SocialAccount {
    @Id
    private String id;
    private String name;
    private String email;
    private String imageUrl;
    private AuthProvider provider;
    private String providerId;
    private String accessToken;
    private String pageAccessToken;

    public SocialAccount() {
    }

    public SocialAccount(String id, String name, String email, String imageUrl, AuthProvider provider, String providerId, String accessToken, String pageAccessToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
        this.accessToken = accessToken;
        this.pageAccessToken = pageAccessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPageAccessToken() {
        return pageAccessToken;
    }

    public void setPageAccessToken(String pageAccessToken) {
        this.pageAccessToken = pageAccessToken;
    }

    @Override
    public String toString() {
        return "SocialAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", provider=" + provider +
                ", providerId='" + providerId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", pageAccessToken='" + pageAccessToken + '\'' +
                '}';
    }
}
