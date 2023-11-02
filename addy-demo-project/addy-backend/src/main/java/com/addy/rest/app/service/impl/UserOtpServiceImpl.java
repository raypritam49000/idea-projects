package com.addy.rest.app.service.impl;

import com.addy.rest.app.dto.UserOtpDTO;
import com.addy.rest.app.mappers.UserOtpMapper;
import com.addy.rest.app.repository.UserOtpRepository;
import com.addy.rest.app.service.UserOtpService;
import javassist.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserOtpServiceImpl implements UserOtpService {

    private final UserOtpRepository userOtpRepository;
    private final Environment env;

    public UserOtpServiceImpl(UserOtpRepository userOtpRepository, Environment env) {
        this.userOtpRepository = userOtpRepository;
        this.env = env;
    }

    @Override
    public UserOtpDTO addUserOtpDTO(UserOtpDTO userOtpDTO) {
        return UserOtpMapper.INSTANCE.toDto(userOtpRepository.save(UserOtpMapper.INSTANCE.toEntity(userOtpDTO)));
    }

    @Override
    public UserOtpDTO getUserOtpByUserId(String userId) {
        return UserOtpMapper.INSTANCE.toDto(userOtpRepository.findByUserId(userId));
    }

    @Override
    public void deleteUserOtp(String userId) throws NotFoundException {
        UserOtpDTO existingUserOtp = getUserOtpByUserId(userId);

        if (ObjectUtils.isEmpty(existingUserOtp)) {
            throw new NotFoundException(env.getProperty("addy.validation.otp_by_user_id_not_found"));
        }
        userOtpRepository.delete(UserOtpMapper.INSTANCE.toEntity(existingUserOtp));
    }


}
