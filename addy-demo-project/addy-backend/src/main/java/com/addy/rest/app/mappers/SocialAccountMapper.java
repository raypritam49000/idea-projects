package com.addy.rest.app.mappers;

import com.addy.rest.app.dto.SocialAccountDTO;
import com.addy.rest.app.mappers.base.BaseMapper;
import com.addy.rest.app.model.SocialAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialAccountMapper extends BaseMapper<SocialAccountDTO, SocialAccount> {
    SocialAccountMapper INSTANCE = Mappers.getMapper(SocialAccountMapper.class);
}
