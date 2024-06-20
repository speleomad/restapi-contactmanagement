package com.example.restapicontactmanagement.business.services;

import java.util.List;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.exceptions.DuplicateContactException;

public interface ContactService {
    public List<Contact> getAllContacts();
    public Contact getContactById(Long id);
    public Contact addContact(Contact  contact) throws DuplicateContactException;
    public Contact updateContact(Long id,Contact contact) throws DuplicateContactException;
    public void deleteContact(Long id);    
    public Contact updateContactImage(Long id,String filename);
}
