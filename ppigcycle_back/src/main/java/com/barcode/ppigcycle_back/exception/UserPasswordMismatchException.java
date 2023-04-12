package com.barcode.ppigcycle_back.exception;

public class UserPasswordMismatchException extends Exception {
    public UserPasswordMismatchException(String message) {
        super(message);
    }
}