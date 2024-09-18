package com.elearn.app.elearn_bak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearn_bak.dtos.UserDto;
import com.elearn.app.elearn_bak.services.UserServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public UserDto createUser (@RequestBody UserDto userDto) {

        return userService.create(userDto);
    }
    // method level security
    @GetMapping("/{user_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  //hasRole('ROLE_ADMIN') or hasAuthority('ROLE_ADMIN') // only admin can access   
    public UserDto getUser (@PathVariable String user_id) {

        return userService.getUser(user_id);
    }
}
