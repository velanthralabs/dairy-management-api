package com.velanthralabs.dairy.features.vendors.exception;

public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException(Long id) {
        super("Vendor with id " + id + " not found");
    }
}
