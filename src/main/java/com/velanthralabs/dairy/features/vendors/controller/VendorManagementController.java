package com.velanthralabs.dairy.features.vendors.controller;

import com.velanthralabs.dairy.features.vendors.dto.VendorRequestDTO;
import com.velanthralabs.dairy.features.vendors.dto.VendorResponseDTO;
import com.velanthralabs.dairy.features.vendors.service.VendorManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorManagementController {

    private final VendorManagementService vendorManagementService;

    @GetMapping("/all")
    public ResponseEntity<List<VendorResponseDTO>> getAll() {
        return ResponseEntity.ok(vendorManagementService.findAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorManagementService.getVendorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody VendorRequestDTO request) {
        vendorManagementService.createVendor(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody VendorRequestDTO request) {
        vendorManagementService.updateVendor(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendorManagementService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}