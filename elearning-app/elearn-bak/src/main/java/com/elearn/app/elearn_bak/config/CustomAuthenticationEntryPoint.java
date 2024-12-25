package com.elearn.app.elearn_bak.config;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.elearn.app.elearn_bak.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //gets executed when unauthenticated use accesses authenticated resource
    @Override
    public void commence(
        
        HttpServletRequest request, 
        HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Invalid details " + authException.getMessage());
        customMessage.setSuccess(false);
        authException.printStackTrace();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(customMessage);

        response.getWriter().write(jsonString);
     
    }
}
