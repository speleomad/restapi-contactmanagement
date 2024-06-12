package com.example.restapicontactmanagement.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapicontactmanagement.business.services.ContactService;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.web.dto.ContactSummaryDTO;

@RestController
@RequestMapping("/api/contacts")

public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllContacts() {
        List<ContactSummaryDTO> contacts = this.contactService.getAllContacts()
                .stream()
                .map(ContactSummaryDTO::toContactSummaryDTO)
                // .map(contact->ContactSummaryDTO.toContactSummaryDTO(contact))
                .collect(Collectors.toList()); 
           // List<Contact> contacts=this.contactService.getAllContacts();
              
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
