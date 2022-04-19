package com.amr.project.exception;

public class ShopAlreadyExistingException extends RuntimeException {
    public ShopAlreadyExistingException(String message) {
        super(message);
    }
}
