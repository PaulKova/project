package com.amr.project.exceptionhandler;

import com.amr.project.exception.ShopAlreadyExistingException;
import com.amr.project.exception.ShopNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopExceptionHandler {

    @ExceptionHandler({ShopAlreadyExistingException.class, ShopNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleExistingShop() {

    }
}
