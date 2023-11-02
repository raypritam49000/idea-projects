package com.addy.rest.app.service;

import com.addy.rest.app.dto.AuthTokenDTO;
import com.addy.rest.app.dto.UserDTO;
import com.addy.rest.app.dto.UserOtpDTO;
import com.addy.rest.app.exception.AddyValidationException;
import jakarta.mail.MessagingException;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;


public interface UserService {
    UserDTO getUserByUsername(String username) throws NotFoundException;

    AuthTokenDTO authenticateUser(String emailOrUsername, String password) throws AddyValidationException;

    UserDTO registerUser(UserDTO userDto) throws AddyValidationException, NotFoundException, MessagingException;

    void softDeleteUser(UserDetails userDetails, String id) throws NotFoundException;

    void permanentDeleteUser(String id) throws NotFoundException;

    UserDTO getUser(String id) throws NotFoundException;

    UserDTO findByUsernameOrEmailAndDeletedFalse(String username, String email);

    UserDTO updateUser(UserDetails userDetails, String id, UserDTO userDTO) throws NotFoundException, AddyValidationException;

    void verifyUserOtp(UserOtpDTO userOtpDTO) throws AddyValidationException, NotFoundException;

    void generateUserOtp(String email) throws NotFoundException, MessagingException;

    List<UserDTO> getUsers();
}
