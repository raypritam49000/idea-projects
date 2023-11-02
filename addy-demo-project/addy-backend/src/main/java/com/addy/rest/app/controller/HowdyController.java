package com.addy.rest.app.controller;

import com.addy.rest.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/howdy")
public class HowdyController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    @PreAuthorize("hasAuthority('USER-R')")
    public String howdy(Principal principal, Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("@@@ howdy Authentication : {} ::: UserDetails : {} ::: Principal : {} ::: User : {} ", authentication, userDetails, principal, user);
        LOGGER.info("@@@ AuthToken ::: " + userDetails.getAuthorities());
        return "howdy";
    }

}
