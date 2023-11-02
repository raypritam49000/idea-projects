package com.addy.rest.app.security.oauth2;

import com.addy.rest.app.dto.SocialAccountDTO;
import com.addy.rest.app.dto.UserDTO;
import com.addy.rest.app.dto.fb.FacebookData;
import com.addy.rest.app.dto.fb.FacebookPageData;
import com.addy.rest.app.enumeration.AuthProvider;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.mappers.UserMapper;
import com.addy.rest.app.security.oauth2.user.OAuth2UserInfo;
import com.addy.rest.app.security.oauth2.user.OAuth2UserInfoFactory;
import com.addy.rest.app.service.SocialAccountService;
import com.addy.rest.app.service.UserService;
import com.addy.rest.app.utilities.CookieUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javassist.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final String FACEBOOK_BASE_URL = "https://graph.facebook.com/v17.0";
    private final SocialAccountService socialAccountService;
    private final Environment env;
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    private final OAuth2UserInfoFactory oAuth2UserInfoFactory;
    private final UserMapper userMapper;

    public CustomOAuth2UserService(UserMapper userMapper,SocialAccountService socialAccountService, Environment env, @Lazy UserService userService, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, OAuth2UserInfoFactory oAuth2UserInfoFactory) {
        this.userMapper = userMapper;
        this.socialAccountService = socialAccountService;
        this.env = env;
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.oAuth2UserInfoFactory = oAuth2UserInfoFactory;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws AddyValidationException, NotFoundException, JsonProcessingException, MessagingException {
        OAuth2UserInfo oAuth2UserInfo = oAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        SocialAccountDTO existingSocialAccountDTO = socialAccountService.getSocialAccountByProviderId(oAuth2UserInfo.getId());

        SocialAccountDTO socialAccountDTO;

        if (ObjectUtils.isNotEmpty(existingSocialAccountDTO)) {
            socialAccountDTO = updateExistingSocialAccount(existingSocialAccountDTO, oAuth2UserInfo);
        } else {
            socialAccountDTO = addSocialAccount(oAuth2UserRequest, oAuth2UserInfo);
        }

        String userId = CookieUtils.getCookie(httpServletRequest, "userId").get().getValue();
        UserDTO userDTO = userService.getUser(userId);
        List<SocialAccountDTO> socialAccountDTOS = new ArrayList<>(userDTO.getSocialAccounts());
        socialAccountDTOS.add(socialAccountDTO);
        userDTO.setSocialAccounts(socialAccountDTOS);

        CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, "userId");

        return userMapper.toEntity(userService.registerUser(userDTO));
    }

    private SocialAccountDTO addSocialAccount(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) throws JsonProcessingException {
        SocialAccountDTO socialAccountDTO = new SocialAccountDTO();
        socialAccountDTO.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        socialAccountDTO.setId(oAuth2UserInfo.getId());
        socialAccountDTO.setProviderId(oAuth2UserInfo.getId());
        socialAccountDTO.setName(oAuth2UserInfo.getName());
        socialAccountDTO.setEmail(oAuth2UserInfo.getEmail());
        socialAccountDTO.setImageUrl(oAuth2UserInfo.getImageUrl());
        socialAccountDTO.setAccessToken(exchangeForLongLivedToken(oAuth2UserRequest.getAccessToken().getTokenValue(), env.getProperty("spring.security.oauth2.client.registration.facebook.clientId"), env.getProperty("spring.security.oauth2.client.registration.facebook.clientSecret")).get("access_token"));
        List<FacebookData> facebookDataList = getFacebookDataList(oAuth2UserRequest, oAuth2UserInfo);
        socialAccountDTO.setPageAccessToken(new ObjectMapper().writeValueAsString(facebookDataList));
        return socialAccountService.addSocialAccountDto(socialAccountDTO);
    }

    private SocialAccountDTO updateExistingSocialAccount(SocialAccountDTO socialAccountDTO, OAuth2UserInfo oAuth2UserInfo) {
        socialAccountDTO.setEmail(socialAccountDTO.getEmail());
        socialAccountDTO.setName(oAuth2UserInfo.getName());
        socialAccountDTO.setImageUrl(oAuth2UserInfo.getImageUrl());
        socialAccountDTO.setProvider(socialAccountDTO.getProvider());
        socialAccountDTO.setPageAccessToken(socialAccountDTO.getPageAccessToken());
        return socialAccountService.addSocialAccountDto(socialAccountDTO);
    }

    private Map<String, String> exchangeForLongLivedToken(String shortLivedToken, String clientId, String clientSecret) {
        String url = FACEBOOK_BASE_URL + "/oauth/access_token?grant_type=fb_exchange_token" + "&client_id=" + clientId + "&client_secret=" + clientSecret + "&fb_exchange_token=" + shortLivedToken;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            // Handle error case
            return null;
        }
    }

    private List<FacebookData> getFacebookDataList(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {

        RestTemplate restTemplate = new RestTemplate();

        String url = FACEBOOK_BASE_URL + "/" + oAuth2UserInfo.getId() + "/accounts?fields=name,access_token&access_token=" + oAuth2UserRequest.getAccessToken().getTokenValue();
        ResponseEntity<FacebookPageData> response = restTemplate.getForEntity(url, FacebookPageData.class);

        return response.getBody().getData().stream().map(data -> {
            FacebookData facebookData = new FacebookData();
            BeanUtils.copyProperties(data, facebookData);
            facebookData.setAccessToken(exchangeForLongLivedToken(data.getAccessToken(), env.getProperty("spring.security.oauth2.client.registration.facebook.clientId"), env.getProperty("spring.security.oauth2.client.registration.facebook.clientSecret")).get("access_token"));
            return facebookData;
        }).collect(Collectors.toList());
    }


}
