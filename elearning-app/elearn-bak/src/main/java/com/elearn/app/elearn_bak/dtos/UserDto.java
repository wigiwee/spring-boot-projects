 package com.elearn.app.elearn_bak.dtos;

import java.util.Date;

public class UserDto {


    private String userId;

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    private boolean active=false;

    private boolean emailVerified = false;

    private boolean smsVerified = false;

    private Date createdAt;

    private String profilePath;

    private String recentOTP;

}
