package com.example.restapicontactmanagement.exceptions;

public class DuplicateContactException extends Exception {
    public DuplicateContactException(String message){
        super(message);
    }
}
