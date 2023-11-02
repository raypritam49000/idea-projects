package com.addy.rest.app.mappers;

import com.addy.rest.app.dto.PermissionDTO;
import com.addy.rest.app.mappers.base.BaseMapper;
import com.addy.rest.app.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDTO, Permission> {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
}
