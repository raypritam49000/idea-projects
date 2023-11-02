package com.addy.rest.app.service.impl;

import com.addy.rest.app.dto.PermissionDTO;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.mappers.PermissionMapper;
import com.addy.rest.app.model.Permission;
import com.addy.rest.app.repository.PermissionRepository;
import com.addy.rest.app.service.PermissionService;
import javassist.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final Environment env;

    public PermissionServiceImpl(PermissionRepository permissionRepository, Environment env) {
        this.permissionRepository = permissionRepository;
        this.env = env;
    }

    @Override
    public PermissionDTO createPermission(UserDetails userDetails, PermissionDTO permissionDTO) throws AddyValidationException {
        validatePermission(permissionDTO);

        Permission existingPermission = permissionRepository.findByNameAndAbbr(permissionDTO.getName(), permissionDTO.getAbbr());

        if (ObjectUtils.isNotEmpty(existingPermission)) {
            throw new IllegalArgumentException(env.getProperty("addy.validation.permission_already_exist"));
        }

        Permission permission = new Permission(permissionDTO.getAbbr().trim(), permissionDTO.getName().trim());
        permission.setCreatedBy(userDetails.getUsername());
        permission.setCreationDate(new Date());

        return PermissionMapper.INSTANCE.toDto(permissionRepository.save(permission));
    }

    @Override
    public void deletePermission(String id) throws NotFoundException {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.permission_not_found")));
        permission.setDeleted(true);
        permissionRepository.save(permission);
    }

    @Override
    public List<PermissionDTO> findAllPermission() {
        List<Permission> permissions = permissionRepository.findAll();
        return PermissionMapper.INSTANCE.toDtoList(permissions);
    }

    @Override
    public PermissionDTO findPermissionById(String id) throws NotFoundException {
        return PermissionMapper.INSTANCE.toDto(permissionRepository.findById(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.permission_not_found"))));
    }

    @Override
    public PermissionDTO updatePermission(UserDetails userDetails, String permissionId, PermissionDTO updatedPermissionDTO) throws NotFoundException, AddyValidationException {
        validatePermission(updatedPermissionDTO);

        Permission existingPermission = permissionRepository.findById(permissionId).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.permission_not_found")));

        if (!existingPermission.getName().equals(updatedPermissionDTO.getName())) {
            Permission conflictingPermission = permissionRepository.findByName(updatedPermissionDTO.getName());
            if (ObjectUtils.isNotEmpty(conflictingPermission)) {
                throw new IllegalArgumentException(env.getProperty("addy.validation.permission_already_exist"));
            }
        }

        existingPermission.setName(updatedPermissionDTO.getName().trim());
        existingPermission.setModificationDate(new Date());
        existingPermission.setModifiedBy(userDetails.getUsername());

        return PermissionMapper.INSTANCE.toDto(permissionRepository.save(existingPermission));
    }

    public void validatePermission(PermissionDTO permissionDTO) throws AddyValidationException {

        if (ObjectUtils.isEmpty(permissionDTO.getName())) {
            throw new AddyValidationException(env.getProperty("addy.validation.name_not_null"));
        }

        if (ObjectUtils.isEmpty(permissionDTO.getAbbr())) {
            throw new AddyValidationException(env.getProperty("addy.validation.abbr_not_null"));
        }

        Permission checkIfExit = permissionRepository.findByName(permissionDTO.getName());

        if (ObjectUtils.isNotEmpty(checkIfExit)) {
            throw new AddyValidationException(env.getProperty("addy.validation.permission_already_exist"));
        }
    }
}
