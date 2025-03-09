package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.exception.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<String> getProductHandler(NoSuchProductException e){
        ShopError shopError = new ShopError(1, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(shopError.toString());
    }
}
