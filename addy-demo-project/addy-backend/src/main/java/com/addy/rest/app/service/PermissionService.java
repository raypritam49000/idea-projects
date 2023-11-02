package com.addy.rest.app.service;

import com.addy.rest.app.dto.PermissionDTO;
import com.addy.rest.app.exception.AddyValidationException;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PermissionService {
    PermissionDTO createPermission(UserDetails userDetails,PermissionDTO permissionDTO) throws AddyValidationException;

    void deletePermission(String id) throws NotFoundException;

    List<PermissionDTO> findAllPermission();

    PermissionDTO findPermissionById(String id) throws NotFoundException;

    PermissionDTO updatePermission(UserDetails userDetails,String id, PermissionDTO permissionDTO) throws NotFoundException, AddyValidationException;

}
