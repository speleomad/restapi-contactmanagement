package com.example.restapicontactmanagement.business.services;

import java.util.List;
import com.example.restapicontactmanagement.dao.entities.Contact;

public interface ContactService {
    public List<Contact> getAllContacts();
    public Contact getContactById(Long id);
    public Contact addContact(Contact  contact) ;
    public Contact updateContact(Long id,Contact contact);
    public void deleteContact(Long id);    
}
