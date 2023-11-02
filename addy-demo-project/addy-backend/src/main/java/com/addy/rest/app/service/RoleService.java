package com.addy.rest.app.service;

import com.addy.rest.app.dto.RoleDTO;
import com.addy.rest.app.dto.RoleRequestDTO;
import com.addy.rest.app.exception.AddyValidationException;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface RoleService {

    List<String> getAllPermissionAbbrByRoleNames(List<String> roleNames);

    RoleDTO createRole(UserDetails userDetails,RoleRequestDTO roleDTO) throws AddyValidationException;

    void deleteRole(String id) throws NotFoundException;

    List<RoleDTO> findAllRoles();

    RoleDTO findRoleById(String id) throws NotFoundException;

    RoleDTO updateRole(UserDetails userDetails,String id, RoleRequestDTO roleDTO) throws NotFoundException, AddyValidationException;

    List<RoleDTO> getRolesByName(List<String> names);

    RoleDTO getRoleByName(String name);
}
