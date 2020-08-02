package com.pantala.todo.auth;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    private final Logger logger;

    private final JwtService jwtService;

    public AuthFilter(Logger logger, JwtService jwtService) {
        this.logger = logger;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authHeader.split(" ")[1];

        try {
            String userId = jwtService.verifyJwt(token);
            servletRequest.setAttribute("userId", userId);
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch(Exception ex){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }
}
