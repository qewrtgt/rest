package com.spring.rest.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        if (roles.contains("ADMIN")) {
        httpServletResponse.sendRedirect("/");
//        } else {
//            httpServletResponse.sendRedirect("/user");
//        }
    }
}
