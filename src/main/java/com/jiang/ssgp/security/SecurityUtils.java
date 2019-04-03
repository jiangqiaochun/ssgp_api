package com.jiang.ssgp.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author jqc
 * @create 2019-03-18 18:45
 */
public class SecurityUtils {
    private SecurityUtils(){
    }
    public static String getCurrentUserId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Optional<String> optionalS = Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });

        return optionalS.orElse("");
    }
}