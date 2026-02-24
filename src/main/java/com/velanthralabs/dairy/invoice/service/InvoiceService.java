package com.velanthralabs.dairy.invoice.service;

import com.velanthralabs.dairy.invoice.domain.Invoice;
import com.velanthralabs.dairy.invoice.repository.InvoiceRepository;
import com.velanthralabs.dairy.purchase.domain.MilkPurchase;
import com.velanthralabs.dairy.purchase.repository.MilkPurchaseRepository;
import com.velanthralabs.dairy.vendors.repository.VendorManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MilkPurchaseRepository purchaseRepository;
    private final VendorManagementRepository vendorRepository;

    @Transactional
    public Invoice generateInvoice(Long vendorId, LocalDate from, LocalDate to) {
        List<MilkPurchase> purchases = purchaseRepository
                .findByVendorIdAndEntryDateBetweenAndInvoicedFalse(vendorId, from, to);

        if (purchases.isEmpty()) throw new RuntimeException("No records found!");

        // 1. Calculations
        BigDecimal totalKgs = purchases.stream()
                .map(MilkPurchase::getQtyKgs)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalLiters = purchases.stream()
                .map(MilkPurchase::getQtyLiters)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalMilkValue = purchases.stream()
                .map(MilkPurchase::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Map to your specific Dairy Business fields
        // (Assuming Net Payable = Total Milk Value for now)
        return invoiceRepository.save(Invoice.builder()
                .vendor(vendorRepository.findById(vendorId).get())
                .invoiceNumber("INV-" + System.currentTimeMillis())
                .fromDate(from)
                .toDate(to)
                .totalKgs(totalKgs)
                .totalLiters(totalLiters)
                .milkValue(totalMilkValue)
                .grossAmount(totalMilkValue)
                .totalAmount(totalMilkValue)
                .netPayable(totalMilkValue)
                .piTotal(BigDecimal.ZERO) // Set to zero or your logic
                .status("GENERATED")
                .build());
    }

}