package com.elearn.app.elearn_bak.services;

import com.elearn.app.elearn_bak.dtos.UserDto;
import com.elearn.app.elearn_bak.entities.Role;
import com.elearn.app.elearn_bak.entities.User;
import com.elearn.app.elearn_bak.repository.RoleRepo;
import com.elearn.app.elearn_bak.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private ModelMapper modelMapper;

    private RoleRepo roleRepo;

    private PasswordEncoder passwordEncoder;

    public UserDto create(UserDto userDto) {


        User user = dtoToEntity(userDto);
        user.setUserId(UUID.randomUUID().toString());
        user.setCreatedAt(new Date());
        user.setEmailVerified(false);
        user.setSmsVerified(false);
        user.setRoles(new HashSet<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role guestRole = roleRepo.findByRoleName("ROLE_GUEST").orElseThrow(() -> {
            throw new RuntimeException("Role not found, contact admin");
        });
        user.assignRole(guestRole);
        User savedUser = userRepo.save(user);
        return entityToDto(savedUser);
    }

    public UserDto entityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto getUser(String user_id) {
        User user = userRepo.findById(user_id).orElseThrow(() -> {
            throw new RuntimeException("User not found");
        });
        return entityToDto(user);
    }
}
