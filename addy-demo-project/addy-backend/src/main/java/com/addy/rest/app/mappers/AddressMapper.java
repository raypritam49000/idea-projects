package com.addy.rest.app.mappers;

import com.addy.rest.app.dto.AddressDTO;
import com.addy.rest.app.mappers.base.BaseMapper;
import com.addy.rest.app.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper extends BaseMapper<AddressDTO, Address> {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
}
