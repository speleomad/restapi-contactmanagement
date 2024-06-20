package com.example.restapicontactmanagement.business.serviceImpl;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.restapicontactmanagement.business.services.ContactService;
import com.example.restapicontactmanagement.business.services.FilesStorageService;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.dao.repositories.ContactRepository;
import com.example.restapicontactmanagement.exceptions.DuplicateContactException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final FilesStorageService filesStorageService;

    public ContactServiceImpl(ContactRepository contactRepository,
                              FilesStorageService filesStorageService) {
        this.contactRepository = contactRepository;
        this.filesStorageService = filesStorageService;
    }



    // Sort contacts by name in ascending alphabetical order
    @Override
    public List<Contact> getAllContacts() {
        return this.contactRepository.findAll(Sort.by(Direction.ASC, "name"));
    }

    @Override
    public Contact getContactById(Long id) {
        // Check if the ID is null and throw an IllegalArgumentException if it is
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // Retrieve the contact by ID, throw an EntityNotFoundException if not found
        return this.contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact with id: " + id + " not found"));
    }

    // Add method
    @Override
    public Contact addContact(Contact contact) throws DuplicateContactException {
        // Check if the contact is null and throw an IllegalArgumentException if it is
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        try {
            // Save the contact in the repository
            return contactRepository.save(contact);
        } catch (DataIntegrityViolationException e) {
            // Handle uniqueness constraint violations
            throw new DuplicateContactException(
                    "A contact with the same email or other unique field already exists.");
        }
    }
   // Update method
    @Override
    public Contact updateContact(Long id, Contact contact) throws DuplicateContactException {
        // Check if the ID or contact is null and throw an IllegalArgumentException if they are
        if (id == null || contact == null) {
            throw new IllegalArgumentException("ID or Contact cannot be null");
        }

        // Verify the existence of the contact
        getContactById(id);

        try {
            // Save the updated contact in the repository
            return contactRepository.save(contact);
        } catch (DataIntegrityViolationException e) {
            // Handle uniqueness constraint violations
            throw new DuplicateContactException(
                    "A contact with the same email or other unique field already exists.");
        }
    }


    @Override
    @Transactional
    // the deleteContact method executes all its operations (checking for the contact, deleting the file, 
    //and deleting the contact record) within a single transaction.If any part of this process fails, 
    //the entire transaction will be rolled back, maintaining data consistency and integrity. 
    public void deleteContact(Long id) {
        // Check if the ID is null and throw an IllegalArgumentException if it is
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        try {
            // Retrieve the contact by ID
            Contact contact = this.getContactById(id);
            // Get the image filename associated with the contact
            String filename = contact.getImage();
            // If the contact has an image, delete it
            if (filename != null) {
                filesStorageService.delete(filename);
            }
            // Delete the contact from the repository by ID
            contactRepository.deleteById(id);
        } catch (DataAccessException e) {
            // Capture any data access exceptions (e.g., foreign key constraint violations)
            throw new RuntimeException("Failed to delete contact with id: " + id, e);
        }
    }
    @Override
    public Contact updateContactImage(Long id, String filename) {
        // Check if the ID is null and throw an IllegalArgumentException if it is
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
      
        // Retrieve the contact by ID, throw an EntityNotFoundException if the contact
        // is not found
        Contact contact = getContactById(id);

        // Check if the contact already has an image
        if (contact.getImage() == null) {
            // If the contact does not have an image, set the new image
            contact.setImage(filename);
        } else {
            // If the contact already has an image, delete the old image
            this.filesStorageService.delete(contact.getImage());
            // Set the new image
            contact.setImage(filename);
        }
        // Save and return the updated contact in the repository
        return contactRepository.save(contact);
    }
}


