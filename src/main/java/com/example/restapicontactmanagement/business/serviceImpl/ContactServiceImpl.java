package com.example.restapicontactmanagement.business.serviceImpl;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.restapicontactmanagement.business.services.ContactService;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.dao.repositories.ContactRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
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

    @Override
    public Contact addContact(Contact contact) {
        // Check if the contact is null and throw an IllegalArgumentException if it is
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
            // Save the contact in the repository
            return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        // Check if the ID or contact is null and throw an IllegalArgumentException if they are
        if (id == null || contact == null) {
            throw new IllegalArgumentException("ID or Contact cannot be null");
        }

        // Verify the existence of the contact
        getContactById(id);

        // Save the updated contact in the repository
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        // Check if the ID is null and throw an IllegalArgumentException if it is
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
            // Retrieve the contact by ID
            this.getContactById(id);
            // Delete the contact from the repository by ID
            contactRepository.deleteById(id);
    }
}
