package com.elearn.app.elearn_bak.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "app_users")
public class User {

    @Id
    private String userId;

    private String name;

    @Column(unique = true)
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
