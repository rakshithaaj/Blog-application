package com.blogPostApp.blogserver.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        // You can customize the response sent when an unauthenticated request is made
        // For example, you can return a 401 Unauthorized status and a custom message
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Customize the response message to suit your application's needs
        String errorMessage = "Unauthorized: You are not authenticated.";
        // You can use a JSON library (e.g., Jackson) to serialize this into JSON format
        String jsonResponse = "{\"error\": \"" + errorMessage + "\"}";
        try {
            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            // Handle exceptions if any
        }
    }
}
