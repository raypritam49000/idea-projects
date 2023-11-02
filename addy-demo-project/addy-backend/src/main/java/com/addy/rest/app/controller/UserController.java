package com.addy.rest.app.controller;

import com.addy.rest.app.dto.UserDTO;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") String id) throws NotFoundException {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void softDeleteUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String id) throws NotFoundException {
        userService.softDeleteUser(userDetails, id);
    }

    @DeleteMapping(value = "/permanent_delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void permanentDeleteUser(@PathVariable("id") String id) throws NotFoundException {
        userService.permanentDeleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String id, @RequestBody UserDTO userDto) throws AddyValidationException, NotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return userService.updateUser(userDetails, id, userDto);
    }

    @GetMapping("/getUserByUsername/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByUsername(@PathVariable("username") String username) throws NotFoundException {
        return userService.getUserByUsername(username);
    }

}
