package com.velanthralabs.dairy.vendors.controller;

import com.velanthralabs.dairy.vendors.dto.VendorDTO;
import com.velanthralabs.dairy.vendors.service.VendorManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorManagementController {
    private final VendorManagementService vendorManagementService;

    @GetMapping("/all")
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        List<VendorDTO> vendors = vendorManagementService.findAllVendors().stream()
                .map(vendor -> {
                    VendorDTO dto = new VendorDTO();
                    // Map fields from vendor to dto
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(vendors);
    }
}
