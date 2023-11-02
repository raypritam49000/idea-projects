package com.addy.rest.app.security;

import com.addy.rest.app.mappers.UserMapper;
import com.addy.rest.app.model.User;
import com.addy.rest.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(@Lazy UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) {
        LOGGER.info("@@@ loadUserByUsername ::: ");
        User userDetails = userMapper.toEntity(userService.findByUsernameOrEmailAndDeletedFalse(emailOrUsername, emailOrUsername));
        return userDetails;
    }

}
