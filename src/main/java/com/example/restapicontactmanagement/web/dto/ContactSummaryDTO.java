package com.example.restapicontactmanagement.web.dto;

import com.example.restapicontactmanagement.dao.entities.Contact;

import lombok.Builder;

@Builder
public record ContactSummaryDTO(
        Long id,
        String name,
        boolean featured) {
public static ContactSummaryDTO toContactSummaryDTO(Contact contact) {
        ContactSummaryDTO contactSummaryDTO = ContactSummaryDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .featured(contact.isFeatured()).build();
        return contactSummaryDTO;
        //Without Builder
 	/*  return new ContactSummaryDTO(contact.getId(), contact.getName(),
         contact.isFeatured());*/
    }
}
