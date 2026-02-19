package com.velanthralabs.dairy.vendors.controller;

import com.velanthralabs.dairy.vendors.dto.*;
import com.velanthralabs.dairy.vendors.service.VendorManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorManagementController {

    private final VendorManagementService service;

    @GetMapping
    public ResponseEntity<List<VendorResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getVendorById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody VendorRequestDTO request) {
        service.createVendor(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody VendorRequestDTO request) {
        service.updateVendor(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}
