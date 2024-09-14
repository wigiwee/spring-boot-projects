package com.elearn.app.elearn_bak;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.elearn.app.elearn_bak.entities.User;
import com.elearn.app.elearn_bak.repository.UserRepo;
import com.elearn.app.elearn_bak.services.CategoryService;

@SpringBootTest
class ElearnBakApplicationTests {

	@Autowired private CategoryService categoryService;

	@Autowired private UserRepo userRepo;

	@Test
	void contextLoads() {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setName("admin");
		user.setEmail("admin@app.com");
		user.setPassword("admin");
		user.setActive(true);
		user.setCreatedAt(new Date());
		user.setEmailVerified(true);
	
		userRepo.save(user);

	}

	@Test
	void testing(){
		categoryService.addCourseToCategory("fb6b15b1-b4a1-4b36-8c3e-d7e5cb20dd97","5d77782f-095b-413b-b8c9-6cec640c1497");
	}
	
}
