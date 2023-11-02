package com.addy.rest.app.security.oauth2.user;

import com.addy.rest.app.enumeration.AuthProvider;
import com.addy.rest.app.exception.AddyValidationException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OAuth2UserInfoFactory {

    private final Environment env;

    public OAuth2UserInfoFactory(Environment env) {
        this.env = env;
    }

    public OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws AddyValidationException {
        if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new AddyValidationException(env.getProperty("addy.validation.social_account_invalid_registration_id"));
        }
    }
}