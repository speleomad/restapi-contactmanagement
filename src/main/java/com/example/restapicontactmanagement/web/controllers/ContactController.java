package com.example.restapicontactmanagement.web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.restapicontactmanagement.business.services.ContactService;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.exceptions.DuplicateContactException;
import com.example.restapicontactmanagement.web.dto.ContactDTO;
import com.example.restapicontactmanagement.web.dto.ContactSummaryDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/contacts")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<?> getAllContacts(Authentication authentication) {
        List<ContactSummaryDTO> contacts = this.contactService.getAllContacts()
                .stream()
                .map(ContactSummaryDTO::toContactSummaryDTO)
                // .map(contact->ContactSummaryDTO.toContactSummaryDTO(contact))
                .collect(Collectors.toList());     
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        ContactDTO contact = ContactDTO.toContactDTO(this.contactService.getContactById(id));
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('ADMIN')")
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO) throws DuplicateContactException {
         Contact contact = ContactDTO.fromContactDTO(contactDTO);
        return new ResponseEntity<>(this.contactService.addContact(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('ADMIN')")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) throws DuplicateContactException{
        Contact contact = ContactDTO.fromContactDTO(contactDTO);
        return new ResponseEntity<>(this.contactService.updateContact(id, contact), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('ADMIN')")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        this.contactService.deleteContact(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }  
}
