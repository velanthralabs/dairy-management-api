package com.velanthralabs.dairy.vendors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalVendorExceptionHandler {

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<String> handleVendorNotFound(VendorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
