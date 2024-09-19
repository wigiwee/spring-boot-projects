package com.elearn.app.elearn_bak.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private String roleId;

    @Column(unique = true)
    private String roleName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

}