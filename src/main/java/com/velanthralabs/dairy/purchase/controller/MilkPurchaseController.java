package com.velanthralabs.dairy.purchase.controller;

import com.velanthralabs.dairy.purchase.domain.MilkPurchase;
import com.velanthralabs.dairy.purchase.service.MilkPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class MilkPurchaseController {

    private final MilkPurchaseService milkPurchaseService;

    // Only Admin and Editor can record milk purchases
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'EDITOR')")
    public ResponseEntity<MilkPurchase> addPurchase(@RequestBody MilkPurchase purchase) {
        return ResponseEntity.ok(milkPurchaseService.saveEntry(purchase));
    }

    // Get all purchases for a specific vendor (useful for the React "History" table)
    @GetMapping("/vendor/{vendorId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'EDITOR', 'VIEWER')")
    public ResponseEntity<List<MilkPurchase>> getVendorPurchases(@PathVariable Long vendorId) {
        // You'll need to add this method to your Service & Repository
        return ResponseEntity.ok(milkPurchaseService.getPurchasesByVendor(vendorId));
    }
}