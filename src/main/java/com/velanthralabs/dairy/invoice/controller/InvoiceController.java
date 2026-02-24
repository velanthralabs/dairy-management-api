package com.velanthralabs.dairy.invoice.controller;

import com.velanthralabs.dairy.invoice.domain.Invoice;
import com.velanthralabs.dairy.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/generate")
    public ResponseEntity<Invoice> createInvoice(
            @RequestParam Long vendorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        return ResponseEntity.ok(invoiceService.generateInvoice(vendorId, from, to));
    }
}