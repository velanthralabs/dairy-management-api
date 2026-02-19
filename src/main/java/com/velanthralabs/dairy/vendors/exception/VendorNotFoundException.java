package com.velanthralabs.dairy.vendors.exception;

public class VendorNotFoundException extends RuntimeException {
    public VendorNotFoundException(String name) {
        super("Vendor with name" + name + " not found");
    }
}
