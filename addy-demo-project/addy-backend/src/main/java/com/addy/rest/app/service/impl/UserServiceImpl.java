package com.addy.rest.app.service.impl;

import com.addy.rest.app.dto.*;
import com.addy.rest.app.exception.*;
import com.addy.rest.app.mappers.AddressMapper;
import com.addy.rest.app.mappers.UserMapper;
import com.addy.rest.app.model.User;
import com.addy.rest.app.repository.UserRepository;
import com.addy.rest.app.security.TokenProvider;
import com.addy.rest.app.service.UserOtpService;
import com.addy.rest.app.service.UserService;
import com.addy.rest.app.utilities.EmailUtilities;
import jakarta.mail.MessagingException;
import javassist.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;
    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailUtilities emailUtilities;
    private final UserOtpService userOtpService;

    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Environment env, UserDetailsService userDetailsService, TokenProvider tokenProvider, AuthenticationManager authenticationManager, EmailUtilities emailUtilities, UserOtpService userOtpService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.emailUtilities = emailUtilities;
        this.userOtpService = userOtpService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserByUsername(String username) throws NotFoundException {
        return userMapper.toDto(userRepository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.user_not_found"))));
    }


    @Override
    public UserDTO registerUser(UserDTO userDto) throws AddyValidationException, NotFoundException, MessagingException {

        validationUser(userDto);

        Optional<User> userOptional = userRepository.findByUsernameOrEmailAndDeletedFalse(userDto.getUsername(), userDto.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("addy.validation.user_already_exist"));
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User createUser = userRepository.save(userMapper.toEntity(userDto));

        UserDTO createUserDto = userMapper.toDto(createUser);

        generateUserOtp(createUserDto.getEmail());

        return createUserDto;
    }

    @Override
    public void generateUserOtp(String email) throws NotFoundException, MessagingException {

        User user = userRepository.findByEmailAndDeletedFalse(email).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.user_not_found")));

        String otpCode = emailUtilities.generateOTP();

        emailUtilities.sendOTPEmail(user.getEmail(), "Otp Verify", otpCode, "otp-email");

        UserOtpDTO userOtpDTO = new UserOtpDTO();
        userOtpDTO.setUserId(user.getId());
        userOtpDTO.setOtp(otpCode);
        userOtpDTO.setCreationDate(new Date());

        userOtpService.addUserOtpDTO(userOtpDTO);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public void softDeleteUser(UserDetails userDetails, String id) throws NotFoundException {
        User existingUser = userRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.user_by_id_not_found")));
        existingUser.setDeleted(true);
        existingUser.setModificationDate(new Date());
        existingUser.setModifiedBy(userDetails.getUsername());
        userRepository.save(existingUser);
    }

    @Override
    public void permanentDeleteUser(String id) throws NotFoundException {
        User existingUser = userRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.user_by_id_not_found")));
        userRepository.delete(existingUser);
    }

    @Override
    public UserDTO getUser(String id) throws NotFoundException {
        return userMapper.toDto(userRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.user_by_id_not_found"))));
    }

    @Override
    public UserDTO findByUsernameOrEmailAndDeletedFalse(String username, String email) {
        return userMapper.toDto(userRepository.findByUsernameOrEmailAndDeletedFalse(username, email).orElseThrow(() -> new UsernameNotFoundException(env.getProperty("addy.validation.username.not.found"))));
    }

    @Override
    public UserDTO updateUser(UserDetails userDetails, String id, UserDTO userDTO) throws NotFoundException, AddyValidationException {
        // validation for dto
        validationUser(userDTO);

        User existingUser = userRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException(env.getProperty("addy.validation.user_by_id_not_found")));

        existingUser.setAddress(AddressMapper.INSTANCE.toEntity(userDTO.getAddress()));
        existingUser.setContactNo(userDTO.getContactNo());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setIndustry(userDTO.getIndustry());
        existingUser.setAccountNonExpired(userDTO.isAccountNonExpired());
        existingUser.setCredentialsNonExpired(userDTO.isAccountNonLocked());
        existingUser.setEnabled(userDTO.isEnabled());
        existingUser.setAccountNonLocked(userDTO.isAccountNonLocked());

        existingUser.setModifiedBy(userDetails.getUsername());
        existingUser.setModificationDate(new Date());

        return userMapper.toDto(userRepository.save(existingUser));
    }

    @Override
    public AuthTokenDTO authenticateUser(String emailOrUsername, String password) throws AddyValidationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(emailOrUsername);
        authenticate(userDetails.getUsername(), password);
        return new AuthTokenDTO(tokenProvider.generateToken(userDetails));
    }

    private void authenticate(String username, String password) throws AddyValidationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AddyValidationException(env.getProperty("addy.validation.user_disabled"));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(env.getProperty("addy.validation.user_bad_credentials"));
        }
    }

    @Override
    public void verifyUserOtp(UserOtpDTO userOtpDTO) throws AddyValidationException, NotFoundException {
        UserOtpDTO existingUserOtp = userOtpService.getUserOtpByUserId(userOtpDTO.getUserId());

        if (ObjectUtils.isEmpty(existingUserOtp)) {
            throw new AddyValidationException(env.getProperty("addy.validation.invalid_otp_provider"));
        }
        // Check if OTP has expired
        if (isOtpExpired(existingUserOtp.getCreationDate().toInstant())) {
            throw new AddyValidationException(env.getProperty("addy.validation.otp_expired"));
        }
        // Check if the OTP matches
        if (!existingUserOtp.getOtp().equals(userOtpDTO.getOtp())) {
            throw new AddyValidationException(env.getProperty("addy.validation.invalid_otp"));
        }

        UserDTO userDTO = getUser(existingUserOtp.getUserId());

        userDTO.setEmailVerified(true);
        userRepository.save(userMapper.toEntity(userDTO));

        userOtpService.deleteUserOtp(existingUserOtp.getUserId());
    }


    private boolean isOtpExpired(Instant creationTime) {
        Instant now = Instant.now();
        Duration elapsed = Duration.between(creationTime, now);
        return elapsed.compareTo(Duration.ofMinutes(15)) > 0; // Check if elapsed time is more than 15 minutes
    }


    private void validationUser(UserDTO user) throws AddyValidationException {

        if (StringUtils.isEmpty(user.getUsername())) {
            throw new AddyValidationException(env.getProperty("addy.validation.email_not_null"));
        }

        if (!isValidEmail(user.getEmail())) {
            throw new AddyValidationException(env.getProperty("addy.validation.invalid_email_format"));
        }

        if (StringUtils.isEmpty(user.getUsername())) {
            throw new AddyValidationException(env.getProperty("addy.validation.username_not_null"));
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            throw new AddyValidationException(env.getProperty("addy.validation.password_not_null"));
        }

        if (StringUtils.isEmpty(user.getContactNo())) {
            throw new AddyValidationException(env.getProperty("addy.validation.contact_no__not_null"));
        }

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }

        return pat.matcher(email).matches();
    }


}
