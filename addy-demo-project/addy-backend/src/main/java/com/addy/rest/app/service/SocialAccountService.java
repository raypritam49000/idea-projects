package com.addy.rest.app.service;

import com.addy.rest.app.dto.SocialAccountDTO;
import javassist.NotFoundException;

public interface SocialAccountService {
    SocialAccountDTO addSocialAccountDto(SocialAccountDTO socialAccountDTO);

    SocialAccountDTO getSocialAccount(String email) throws NotFoundException;

    SocialAccountDTO getSocialAccountByProviderId(String providerId) throws NotFoundException;
}
