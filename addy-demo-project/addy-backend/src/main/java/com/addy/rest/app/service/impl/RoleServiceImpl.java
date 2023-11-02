package com.addy.rest.app.service.impl;

import com.addy.rest.app.dto.RoleDTO;
import com.addy.rest.app.dto.RoleRequestDTO;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.mappers.RoleMapper;
import com.addy.rest.app.model.Permission;
import com.addy.rest.app.model.Role;
import com.addy.rest.app.repository.PermissionRepository;
import com.addy.rest.app.repository.RoleRepository;
import com.addy.rest.app.service.RoleService;
import javassist.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final Environment env;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository, Environment env) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.env = env;
    }

    @Override
    public List<RoleDTO> getRolesByName(List<String> names) {
        return RoleMapper.INSTANCE.toDtoList(roleRepository.findAllByNameInAndDeletedFalse(names));
    }

    @Override
    public RoleDTO getRoleByName(String name) {
        return RoleMapper.INSTANCE.toDto(roleRepository.findByName(name));
    }


    @Override
    public List<String> getAllPermissionAbbrByRoleNames(List<String> roleNames) {
        List<String> permissionAbbrList = new ArrayList<>();

        for (String roleName : roleNames) {
            Role role = roleRepository.findFirstByName(roleName);
            Set<Permission> permissions = role.getPermissions();

            for (Permission permission : permissions) {
                permissionAbbrList.add(permission.getAbbr());
            }
        }
        return permissionAbbrList;
    }

    @Override
    public RoleDTO createRole(UserDetails userDetails, RoleRequestDTO roleDTO) throws AddyValidationException {

        validateRole(roleDTO);

        Role existingRole = roleRepository.findFirstByName(StringUtils.trimToNull(roleDTO.getName()));

        if (ObjectUtils.isNotEmpty(existingRole)) {
            throw new IllegalArgumentException(env.getProperty("addy.validation.role_already_exist"));
        }

        Role role = new Role(StringUtils.trimToNull(roleDTO.getName()), convertPermissionNamesToPermissions(roleDTO.getPermissions()));
        role.setCreatedBy(userDetails.getUsername());
        role.setCreationDate(new Date());
        return RoleMapper.INSTANCE.toDto(roleRepository.save(role));
    }

    @Override
    public void deleteRole(String id) throws NotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.role_not_found_by_id")));
        role.setDeleted(true);
        roleRepository.save(role);
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        return RoleMapper.INSTANCE.toDtoList(roleRepository.findAll());
    }

    @Override
    public RoleDTO findRoleById(String id) throws NotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.role_not_found_by_id")));
        return RoleMapper.INSTANCE.toDto(role);
    }

    @Override
    public RoleDTO updateRole(UserDetails userDetails, String id, RoleRequestDTO roleDTO) throws NotFoundException, AddyValidationException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.role_not_found_by_id")));
        validateRole(roleDTO);

        if (!role.getName().equalsIgnoreCase(roleDTO.getName())) {
            Role conflictingRole = roleRepository.findFirstByName(roleDTO.getName());
            if (conflictingRole != null) {
                throw new IllegalArgumentException(env.getProperty("addy.validation.role_already_exist"));
            }
        }

        role.setName(StringUtils.trimToNull(roleDTO.getName()));
        role.setPermissions(convertPermissionNamesToPermissions(roleDTO.getPermissions()));
        role.setModificationDate(new Date());
        role.setModifiedBy(userDetails.getUsername());

        return RoleMapper.INSTANCE.toDto(roleRepository.save(role));
    }

    private Set<Permission> convertPermissionNamesToPermissions(Set<String> permissions) {
        Set<Permission> permissionNames = new HashSet<>();
        for (String rolePermission : permissions) {
            if (StringUtils.isNotEmpty(rolePermission)) {
                permissionNames.add(permissionRepository.findByName(rolePermission));
            }
        }
        return permissionNames;
    }

    private void validateRole(RoleRequestDTO roleDTO) throws AddyValidationException {
        if (StringUtils.isEmpty(roleDTO.getName())) {
            throw new AddyValidationException(env.getProperty("addy.validation.name_not_null"));
        }

        if (ObjectUtils.isEmpty(roleDTO.getPermissions())) {
            throw new AddyValidationException(env.getProperty("addy.validation.permission_not_null"));
        }
    }

}
