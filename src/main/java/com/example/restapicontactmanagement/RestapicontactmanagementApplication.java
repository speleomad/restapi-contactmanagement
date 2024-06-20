package com.example.restapicontactmanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.restapicontactmanagement.business.services.FilesStorageService;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.dao.repositories.ContactRepository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RestapicontactmanagementApplication implements CommandLineRunner {

	@Resource
	ContactRepository contactRepository;
	@Resource
	FilesStorageService filesStorageService;
	public static void main(String[] args) {
		SpringApplication.run(RestapicontactmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Storage initialisation");
		filesStorageService.init();
   
	/*	List<String> projects=new ArrayList<>();
          projects.add("p1");
		  projects.add("p2");*/
	// this.contactRepository.save(new Contact(null,"demo1","demo1@gmail.com","http://monsite.fr",projects,null,true));
		
	}


}
