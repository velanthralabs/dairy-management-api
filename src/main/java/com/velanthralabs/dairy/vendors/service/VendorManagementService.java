package com.velanthralabs.dairy.vendors.service;

import com.velanthralabs.dairy.vendors.dto.*;

import java.util.List;

public interface VendorManagementService {

    List<VendorResponseDTO> findAllVendors();

    VendorResponseDTO getVendorById(Long id);

    void createVendor(VendorRequestDTO request);

    void updateVendor(Long id, VendorRequestDTO request);

    void deleteVendor(Long id);
}
