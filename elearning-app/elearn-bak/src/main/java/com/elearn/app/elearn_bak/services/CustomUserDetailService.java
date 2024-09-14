package com.elearn.app.elearn_bak.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearn.app.elearn_bak.entities.CustomUserDetails;
import com.elearn.app.elearn_bak.entities.User;
import com.elearn.app.elearn_bak.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new BadCredentialsException(username));

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user);

        return userDetails;
    }


    
}
