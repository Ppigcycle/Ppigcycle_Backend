package com.barcode.ppigcycle_back.exception;

public class UserDuplicatedException extends RuntimeException {
    public UserDuplicatedException(String message) {
        super(message);
    }
}

