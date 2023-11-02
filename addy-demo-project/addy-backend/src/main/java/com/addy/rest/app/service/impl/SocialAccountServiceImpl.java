package com.addy.rest.app.service.impl;

import com.addy.rest.app.dto.SocialAccountDTO;
import com.addy.rest.app.mappers.SocialAccountMapper;
import com.addy.rest.app.repository.SocialAccountRepository;
import com.addy.rest.app.service.SocialAccountService;
import javassist.NotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SocialAccountServiceImpl implements SocialAccountService {

    private final SocialAccountRepository socialAccountRepository;
    private final Environment env;

    public SocialAccountServiceImpl(SocialAccountRepository socialAccountRepository, Environment env) {
        this.socialAccountRepository = socialAccountRepository;
        this.env = env;
    }

    @Override
    public SocialAccountDTO addSocialAccountDto(SocialAccountDTO socialAccountDTO) {
        return SocialAccountMapper.INSTANCE.toDto(socialAccountRepository.save(SocialAccountMapper.INSTANCE.toEntity(socialAccountDTO)));
    }

    @Override
    public SocialAccountDTO getSocialAccount(String email) throws NotFoundException {
        return SocialAccountMapper.INSTANCE.toDto(socialAccountRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.social_account_not_found_by_email"))));
    }

    @Override
    public SocialAccountDTO getSocialAccountByProviderId(String providerId) throws NotFoundException {
        return SocialAccountMapper.INSTANCE.toDto(socialAccountRepository.findByProviderId(providerId).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.social_account_not_found_by_id"))));
    }


}
