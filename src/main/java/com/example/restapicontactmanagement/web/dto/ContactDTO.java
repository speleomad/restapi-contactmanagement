package com.example.restapicontactmanagement.web.dto;

import java.util.List;

import com.example.restapicontactmanagement.dao.entities.Contact;

import lombok.Builder;

@Builder
public record ContactDTO(
    Long id,
    String name,
    String email,
    String website,
    List<String> projects,
    String image,
    boolean featured) {
  public static ContactDTO toContactDTO(Contact contact) {
    ContactDTO contactDTO = ContactDTO.builder()
        .id(contact.getId())
        .name(contact.getName())
        .email(contact.getEmail())
        .website(contact.getWebsite())
        .projects(contact.getProjects())
        .image(contact.getImage())
        .featured(contact.isFeatured()).build();
    return contactDTO;
     //Without builder
    /* 
     * return new ContactDTO(contact.getId(), contact.getName(), contact.getEmail(),
     * contact.getWebsite(), contact.getProjects(),
     * contact.getImage(), contact.isFeatured());
     */
  }

  public static Contact fromContactDTO(ContactDTO contactDTO) {
    Contact contact = Contact.builder()
        .id(contactDTO.id)
        .name(contactDTO.name)
        .email(contactDTO.email)
        .website(contactDTO.website)
        .projects(contactDTO.projects)
        .image(contactDTO.image)
        .featured(contactDTO.featured).build();
    return contact;
    //Without builder
    /*
     * return new Contact(contactDTO.id, contactDTO.name, contactDTO.email,
     * contactDTO.website, contactDTO.projects,
     * contactDTO.image, contactDTO.featured);
     */
  }
}
