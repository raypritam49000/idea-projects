package com.addy.rest.app.controller;

import com.addy.rest.app.dto.PermissionDTO;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.service.PermissionService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public PermissionDTO createPermission(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PermissionDTO permissionDTO) throws AddyValidationException {
        return permissionService.createPermission(userDetails,permissionDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePermission(@PathVariable String id) throws NotFoundException {
        permissionService.deletePermission(id);
    }

    @GetMapping
    public List<PermissionDTO> findAllPermissions() {
        return permissionService.findAllPermission();
    }

    @GetMapping(value = "/{id}")
    public PermissionDTO findPermissionById(@PathVariable String id) throws NotFoundException {
        return permissionService.findPermissionById(id);
    }

    @PutMapping(value = "/{id}")
    public PermissionDTO updatePermission(@AuthenticationPrincipal UserDetails userDetails,@PathVariable String id, @RequestBody PermissionDTO permissionDTO) throws AddyValidationException, NotFoundException {
        return permissionService.updatePermission(userDetails,id, permissionDTO);
    }

}
