package com.fredo.book_travel.exception.customExceptions;

public class InvalidRoleException extends RuntimeException{

    public InvalidRoleException(String message) {
        super(message);
    }
}
