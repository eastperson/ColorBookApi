//package com.colorbook.api.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
//        logger.error("{}", request.getRequestURI());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//    }
//}
