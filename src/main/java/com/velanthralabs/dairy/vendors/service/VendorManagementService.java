package com.velanthralabs.dairy.vendors.service;

import com.velanthralabs.dairy.vendors.domain.Vendor;

import java.util.List;

public interface VendorManagementService {
    List<Vendor> findAllVendors();
    void createVendor(String name, String contactInfo);
    void updateVendor(Long vendorId, String name, String contactInfo);
    void deleteVendor(Long vendorId);
}
