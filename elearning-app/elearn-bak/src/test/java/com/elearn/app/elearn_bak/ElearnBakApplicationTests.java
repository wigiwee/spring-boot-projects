package com.elearn.app.elearn_bak;

import com.elearn.app.elearn_bak.config.security.JwtUtil;
import com.elearn.app.elearn_bak.entities.Role;
import com.elearn.app.elearn_bak.entities.User;
import com.elearn.app.elearn_bak.repository.RoleRepo;
import com.elearn.app.elearn_bak.repository.UserRepo;
import com.elearn.app.elearn_bak.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class ElearnBakApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo repo;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName("admin");
        user.setEmail("admin@app.com");
        user.setPassword((passwordEncoder.encode("admin")));
        user.setActive(true);
        user.setCreatedAt(new Date());
        user.setEmailVerified(true);
        // Role admin = repo.findByRoleName("ROLE_ADMIN").get();
        Role guest = repo.findByRoleName("ROLE_GUEST").get();
        user.assignRole(guest);
        // user.assignRole(admin);

        userRepo.save(user);

    }

    @Test
    void testing() {
    }

    @Test
    void CreateRoles() {
        Role role1 = new Role();
        Role role2 = new Role();

        role1.setRoleId(UUID.randomUUID().toString());
        role2.setRoleId(UUID.randomUUID().toString());

        role1.setRoleName("GUEST");
        role2.setRoleName("ADMIN");

        repo.save(role1);
        repo.save(role2);
    }

    @Autowired private JwtUtil jwtUtil; 

    @Test
    public void test(){

        System.out.println("testing jwt");

        String token = jwtUtil.generateToken("Kaustubh");

        System.out.println(token);

        System.out.println(jwtUtil.validateToken(token, "Kaustubh"));
        System.out.println(jwtUtil.extractUsername(token));
    }
}
