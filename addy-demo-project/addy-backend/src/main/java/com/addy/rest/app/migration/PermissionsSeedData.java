package com.addy.rest.app.migration;

import com.addy.rest.app.model.Address;
import com.addy.rest.app.model.Permission;
import com.addy.rest.app.model.Role;
import com.addy.rest.app.model.User;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.github.cloudyrock.spring.v5.EnableMongock;
import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EnableMongock
@ChangeLog(order = "001")
public class PermissionsSeedData {

    @ChangeSet(order = "001", id = "savePermission", author = "Pritam Ray", runAlways = true)
    public void savePermission(MongockTemplate mongockTemplate) {
        Set<Permission> permissionsToAdd = Set.of(
                new Permission("USER-C", "User Create"),
                new Permission("USER-R", "User Read"),
                new Permission("USER-D", "User Delete"),
                new Permission("USER-U", "User Update"),
                new Permission("USER-V", "User View"),
                new Permission("ROLE-C", "Role Create"),
                new Permission("ROLE-R", "Role Read"),
                new Permission("ROLE-D", "Role Delete"),
                new Permission("ROLE-U", "Role Update"),
                new Permission("ROLE-V", "Role View"),
                new Permission("PERM-C", "Permission Create"),
                new Permission("PERM-R", "Permission Read"),
                new Permission("PERM-D", "Permission Delete"),
                new Permission("PERM-U", "Permission Update"),
                new Permission("PERM-V", "Permission View")
        );

        Set<Permission> existingPermissions = new HashSet<>(mongockTemplate.findAll(Permission.class));

        Set<Permission> permissionsToInsert = new HashSet<>(permissionsToAdd);
        permissionsToInsert.removeIf(existingPermission -> existingPermissions.stream().anyMatch(newPermission -> newPermission.getName().equals(existingPermission.getName())));

        if (!CollectionUtils.isEmpty(permissionsToInsert) && permissionsToInsert.size() > 0) {
            permissionsToInsert.stream().map(mongockTemplate::save).collect(Collectors.toSet());
        }

    }

    @ChangeSet(order = "002", id = "saveRole", author = "Pritam Ray", runAlways = true)
    public void saveRole(MongockTemplate mongockTemplate) {
        long adminRoleCount = countRoleByName(mongockTemplate, "Admin");
        long superAdminRoleCount = countRoleByName(mongockTemplate, "Super-Admin");
        long userRoleCount = countRoleByName(mongockTemplate, "User");

        if (adminRoleCount == 0) {
            Role adminRole = new Role();
            adminRole.setName("Admin");
            List<String> adminPermissions = List.of("User Create", "User Read", "User Delete", "User View", "Role Create", "Role Read", "Role Delete", "Role Update", "Role View", "Permission Create", "Permission Read", "Permission Delete", "Permission Update", "Permission View");
            adminRole.setPermissions(fetchedPermissions(adminPermissions, mongockTemplate));
            mongockTemplate.save(adminRole);
        }

        if (superAdminRoleCount == 0) {
            Role superAdminRole = new Role();
            superAdminRole.setName("Super-Admin");
            List<String> superAdminPermissions = List.of("User Create", "User Read", "User Delete", "User Update", "User View", "Role Create", "Role Read", "ROLE-D", "Role Delete", "Role Update", "Role View", "Permission Create", "Permission Read", "Permission Delete", "Permission Update", "Permission View");
            superAdminRole.setPermissions(fetchedPermissions(superAdminPermissions, mongockTemplate));
            mongockTemplate.save(superAdminRole);
        }


        if (userRoleCount == 0) {
            Role userRole = new Role();
            userRole.setName("User");
            List<String> userPermissions = List.of("User Read", "User View", "Role Read", "Role View", "Permission Read", "Permission View");
            userRole.setPermissions(fetchedPermissions(userPermissions, mongockTemplate));
            mongockTemplate.save(userRole);
        }

    }


    @ChangeSet(order = "003", id = "saveUser", author = "Pritam Ray", runAlways = true)
    public void saveUser(MongockTemplate mongockTemplate, PasswordEncoder passwordEncoder) {

        long adminUserCount = countUserByUsername(mongockTemplate, "admin");
        long superAdminUserCount = countUserByUsername(mongockTemplate, "super-admin");
        long userCount = countUserByUsername(mongockTemplate, "user");

        Address address = new Address();
        address.setCity("Mohali");
        address.setState("Punjab");
        address.setCountry("Indian");
        address.setHouseNo("454");
        address.setPinCode("140000");

        if (adminUserCount == 0) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setIndustry("IT");
            admin.setEmail("admin@gmail.com");
            admin.setAddress(address);
            admin.setContactNo("7483373822");
            admin.setRoles(fetchedRoles(List.of("Admin"), mongockTemplate));
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            admin.setEnabled(true);
            admin.setCreatedBy("Admin");
            admin.setEmailVerified(true);
            admin.setCreationDate(new Date());

            mongockTemplate.save(admin);
        }

        if (superAdminUserCount == 0) {

            User superAdmin = new User();
            superAdmin.setUsername("super-admin");
            superAdmin.setIndustry("IT");
            superAdmin.setEmail("super-admin@gmail.com");
            superAdmin.setAddress(address);
            superAdmin.setContactNo("7483373822");
            superAdmin.setRoles(fetchedRoles(List.of("Super-Admin"), mongockTemplate));
            superAdmin.setPassword(passwordEncoder.encode("super-admin"));
            superAdmin.setAccountNonExpired(true);
            superAdmin.setAccountNonLocked(true);
            superAdmin.setCredentialsNonExpired(true);
            superAdmin.setEnabled(true);
            superAdmin.setCreatedBy("Super-Admin");
            superAdmin.setEmailVerified(true);
            superAdmin.setCreationDate(new Date());

            mongockTemplate.save(superAdmin);
        }

        if (userCount == 0) {

            User user = new User();
            user.setUsername("user");
            user.setIndustry("IT");
            user.setEmail("user@gmail.com");
            user.setAddress(address);
            user.setContactNo("7483373822");
            user.setRoles(fetchedRoles(List.of("Admin"), mongockTemplate));
            user.setPassword(passwordEncoder.encode("admin"));
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setCreatedBy("User");
            user.setEmailVerified(true);
            user.setCreationDate(new Date());

            mongockTemplate.save(user);
        }

    }


    // Fetch roles by name
    private List<Role> fetchedRoles(List<String> roleNames, MongockTemplate mongockTemplate) {

        return mongockTemplate.findAll(Role.class)
                .stream()
                .filter(role -> roleNames.contains(role.getName()))
                .collect(Collectors.toList());
    }

    // Fetch permissions by name
    private Set<Permission> fetchedPermissions(List<String> permissionNames, MongockTemplate mongockTemplate) {

        return mongockTemplate.findAll(Permission.class)
                .stream()
                .filter(permission -> permissionNames.contains(permission.getName()))
                .collect(Collectors.toSet());
    }

    // count role by role name
    private long countRoleByName(MongockTemplate mongockTemplate, String roleName) {
        Query query = new Query(Criteria.where("name").is(roleName));
        return mongockTemplate.count(query, Role.class);
    }

    // count user by username
    private long countUserByUsername(MongockTemplate mongockTemplate, String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongockTemplate.count(query, User.class);
    }
}
