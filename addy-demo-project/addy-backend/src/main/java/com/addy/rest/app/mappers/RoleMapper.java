package com.addy.rest.app.mappers;

import com.addy.rest.app.dto.RoleDTO;
import com.addy.rest.app.mappers.base.BaseMapper;
import com.addy.rest.app.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDTO, Role> {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Override
    RoleDTO toDto(Role entity);

    @Override
    Role toEntity(RoleDTO dto);

    @Override
    List<RoleDTO> toDtoList(List<Role> entities);

    @Override
    List<Role> toEntityList(List<RoleDTO> entities);

}
