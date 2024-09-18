package com.elearn.app.elearn_bak;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.elearn.app.elearn_bak.entities.Role;
import com.elearn.app.elearn_bak.entities.User;
import com.elearn.app.elearn_bak.repository.RoleRepo;
import com.elearn.app.elearn_bak.repository.UserRepo;
import com.elearn.app.elearn_bak.services.CategoryService;

@SpringBootTest
class ElearnBakApplicationTests {

	@Autowired private CategoryService categoryService;

	@Autowired private UserRepo userRepo;

	@Autowired private PasswordEncoder passwordEncoder;
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
	void testing(){
	}
	
	@Autowired
	private RoleRepo repo;
	@Test
	void CreateRoles(){
		Role role1 = new Role();
		Role role2 = new Role();
		
		role1.setRoleId(UUID.randomUUID().toString());
		role2.setRoleId(UUID.randomUUID().toString());

		role1.setRoleName("GUEST");
		role2.setRoleName("ADMIN");
		
		repo.save(role1);
		repo.save(role2);
	}
}
