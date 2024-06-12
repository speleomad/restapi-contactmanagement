package com.example.restapicontactmanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.dao.repositories.ContactRepository;

import jakarta.annotation.Resource;

@SpringBootApplication
public class RestapicontactmanagementApplication implements CommandLineRunner {

	@Resource
	ContactRepository contactRepository;
	public static void main(String[] args) {
		SpringApplication.run(RestapicontactmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> projects=new ArrayList<>();
          projects.add("p1");
		  projects.add("p2");
	// this.contactRepository.save(new Contact(null,"demo1","demo1@gmail.com","http://monsite.fr",projects,null,true));
		
	}


}
