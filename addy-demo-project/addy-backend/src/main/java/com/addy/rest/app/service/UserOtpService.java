package com.addy.rest.app.service;

import com.addy.rest.app.dto.UserOtpDTO;
import javassist.NotFoundException;

import java.lang.reflect.InvocationTargetException;


public interface UserOtpService {
    UserOtpDTO addUserOtpDTO(UserOtpDTO userOtpDTO);

    UserOtpDTO getUserOtpByUserId(String userId);

    void deleteUserOtp(String userId) throws NotFoundException;
}
