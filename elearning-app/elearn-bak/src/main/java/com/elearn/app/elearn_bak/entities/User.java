package com.elearn.app.elearn_bak.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public void assignRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
