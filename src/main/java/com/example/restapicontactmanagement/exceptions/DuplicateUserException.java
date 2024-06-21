package com.example.restapicontactmanagement.exceptions;

public class DuplicateUserException extends Exception {
    public DuplicateUserException(String message){
        super(message);
    }
}
