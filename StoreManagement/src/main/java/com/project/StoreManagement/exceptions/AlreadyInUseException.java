package com.project.StoreManagement.exceptions;

public class AlreadyInUseException extends RuntimeException {
    public AlreadyInUseException(String message) {
        super(message);
    }
}
