package com.elearn.app.elearn_bak;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elearn.app.elearn_bak.entities.Role;
import com.elearn.app.elearn_bak.repository.RoleRepo;

@SpringBootApplication
public class ElearnBakApplication {

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(ElearnBakApplication.class, args);

		
		
	


	}
}
