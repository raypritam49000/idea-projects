package com.addy.rest.app.mappers;

import com.addy.rest.app.dto.AddressDTO;
import com.addy.rest.app.dto.RoleDTO;
import com.addy.rest.app.dto.UserDTO;
import com.addy.rest.app.model.Address;
import com.addy.rest.app.model.Permission;
import com.addy.rest.app.model.User;
import com.addy.rest.app.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserDTO toDto(User user) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);

        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(user.getAddress(), addressDTO);

        userDto.setAddress(addressDTO);

        List<String> rolesNames = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        userDto.setRoles(rolesNames);

        Set<String> authorities = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).map(Permission::getAbbr).collect(Collectors.toSet());
        userDto.setAuthorities(authorities);

        return userDto;
    }

    public User toEntity(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        Address address = new Address();
        BeanUtils.copyProperties(userDto.getAddress(), address);

        user.setAddress(address);

        List<RoleDTO> roles = roleService.getRolesByName(userDto.getRoles());
        user.setRoles(RoleMapper.INSTANCE.toEntityList(roles));

        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permissionDTO -> permissionDTO.getAbbr())
                .collect(Collectors.toSet())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        user.setAuthorities(authorities);
        return user;
    }


    public List<UserDTO> toDtoList(List<User> entities) {
        return entities.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

    public List<User> toEntityList(List<UserDTO> userDTOS) {
        return userDTOS.stream().map(userDTO -> toEntity(userDTO)).collect(Collectors.toList());
    }

}
