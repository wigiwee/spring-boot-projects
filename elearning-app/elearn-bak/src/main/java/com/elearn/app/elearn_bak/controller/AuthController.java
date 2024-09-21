package com.elearn.app.elearn_bak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearn_bak.config.security.JwtUtil;
import com.elearn.app.elearn_bak.dtos.JwtResponse;
import com.elearn.app.elearn_bak.dtos.LoginRequest;
import com.elearn.app.elearn_bak.entities.CustomUserDetails;
import com.elearn.app.elearn_bak.services.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(
            @RequestBody LoginRequest loginRequest) {

        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticated = authenticationManager.authenticate(auth);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // authenticated
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(loginRequest.getUsername());

        JwtResponse jwtResponse = JwtResponse.builder()
                .token(token)
                .user(userService.entityToDto(userDetails.getUser()))
                .build();

        return ResponseEntity.ok(jwtResponse);
    }
}
