package com.elearn.app.elearn_bak.dtos;

import com.elearn.app.elearn_bak.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDto {


    private String userId;

    private String name;

    private String email;

    private String phoneNumber;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean active = false;

    @JsonIgnore
    private boolean emailVerified = false;
    
    @JsonIgnore
    private boolean smsVerified = false;

    private Date createdAt;

    private String profilePath;

    @JsonIgnore
    private String recentOTP;

    private Set<Role> roles;
}
