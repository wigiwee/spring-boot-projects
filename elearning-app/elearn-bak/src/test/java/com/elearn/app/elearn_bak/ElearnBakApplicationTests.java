package com.elearn.app.elearn_bak;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.elearn.app.elearn_bak.services.CategoryService;

@SpringBootTest
class ElearnBakApplicationTests {

	@Autowired private CategoryService categoryService;

	@Test
	void contextLoads() {
	}

	@Test
	void testing(){
		categoryService.addCourseToCategory("fb6b15b1-b4a1-4b36-8c3e-d7e5cb20dd97","5d77782f-095b-413b-b8c9-6cec640c1497");
	}
	
}
