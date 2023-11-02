package com.addy.rest.app.controller;

import com.addy.rest.app.dto.AuthRequestDTO;
import com.addy.rest.app.dto.AuthTokenDTO;
import com.addy.rest.app.dto.UserDTO;
import com.addy.rest.app.dto.UserOtpDTO;
import com.addy.rest.app.exception.AddyValidationException;
import com.addy.rest.app.service.UserService;
import jakarta.mail.MessagingException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerUser(@RequestBody UserDTO userDto) throws AddyValidationException, NotFoundException, MessagingException {
        return userService.registerUser(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthTokenDTO authenticateUser(@RequestBody AuthRequestDTO authRequestDto) throws AddyValidationException {
        return userService.authenticateUser(authRequestDto.getUsername(), authRequestDto.getPassword());
    }

    @PostMapping("/verifyOtp")
    @ResponseStatus(HttpStatus.OK)
    public void verifyUserOtp(@RequestBody UserOtpDTO userOtpDTO) throws AddyValidationException, NotFoundException {
        userService.verifyUserOtp(userOtpDTO);
    }

    @PostMapping("/resendOtp")
    @ResponseStatus(HttpStatus.OK)
    public void generateUserOtp(@RequestParam("email") String email) throws NotFoundException, MessagingException {
        userService.generateUserOtp(email);
    }

}
