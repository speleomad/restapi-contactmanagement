package com.example.restapicontactmanagement.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapicontactmanagement.dao.entities.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
