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
		user.setName("kaustubh");
		user.setEmail("kaustubh@app.com");
		user.setPassword((passwordEncoder.encode("password")));
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
		categoryService.addCourseToCategory("fb6b15b1-b4a1-4b36-8c3e-d7e5cb20dd97","5d77782f-095b-413b-b8c9-6cec640c1497");
	}
	
	@Autowired
	private RoleRepo repo;
	@Test
	void CreateRoles(){
		Role role1 = new Role();
		Role role2 = new Role();
		
		role1.setRoleId(UUID.randomUUID().toString());
		role2.setRoleId(UUID.randomUUID().toString());

		role1.setRoleName("ROLE_GUEST");
		role2.setRoleName("ROLE_ADMIN");
		
		repo.save(role1);
		repo.save(role2);
	}
}
