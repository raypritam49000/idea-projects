package com.addy.rest.app.controller;

import com.addy.rest.app.dto.RoleDTO;
import com.addy.rest.app.dto.RoleRequestDTO;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.service.RoleService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleDTO createRole(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RoleRequestDTO roleDTO) throws AddyValidationException {
        return roleService.createRole(userDetails, roleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable String id) throws NotFoundException {
        roleService.deleteRole(id);
    }

    @GetMapping
    public List<RoleDTO> findAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO findRoleById(@PathVariable String id) throws NotFoundException {
        return roleService.findRoleById(id);
    }

    @PutMapping(value = "/{id}")
    public RoleDTO updateRole(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id, @RequestBody RoleRequestDTO roleDTO) throws AddyValidationException, NotFoundException {
        return roleService.updateRole(userDetails, id, roleDTO);
    }

    @PostMapping(value = "/roleNames")
    public List<String> getPermissionsByRoleNames(@RequestBody List<String> roles) {
        return roleService.getAllPermissionAbbrByRoleNames(roles);
    }

}
