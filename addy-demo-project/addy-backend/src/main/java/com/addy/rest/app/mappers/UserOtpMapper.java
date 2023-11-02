package com.addy.rest.app.mappers;

import com.addy.rest.app.dto.UserOtpDTO;
import com.addy.rest.app.mappers.base.BaseMapper;
import com.addy.rest.app.model.UserOtp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserOtpMapper extends BaseMapper<UserOtpDTO, UserOtp> {
    UserOtpMapper INSTANCE = Mappers.getMapper(UserOtpMapper.class);
}
