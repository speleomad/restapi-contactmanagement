package com.example.restapicontactmanagement.business.services;

import org.springframework.security.core.Authentication;
import com.example.restapicontactmanagement.dao.entities.User;
import com.example.restapicontactmanagement.exceptions.DuplicateUserException;
import com.example.restapicontactmanagement.web.dto.AuthenticationUserDTO;
public interface AuthenticationService {
   
    User register(User user) throws DuplicateUserException;
   AuthenticationUserDTO login(Authentication authentication);
}
