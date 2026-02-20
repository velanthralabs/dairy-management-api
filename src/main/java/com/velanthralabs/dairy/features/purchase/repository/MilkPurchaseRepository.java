package com.velanthralabs.dairy.features.purchase.repository;

import com.velanthralabs.dairy.features.purchase.domain.MilkPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MilkPurchaseRepository extends JpaRepository<MilkPurchase, Long> {
    List<MilkPurchase> findByVendorId(Long vendorId);

    // This is for your Invoice logic
    List<MilkPurchase> findByVendorIdAndEntryDateBetweenAndInvoicedFalse(
            Long vendorId,
            LocalDate startDate,
            LocalDate endDate
    );
}