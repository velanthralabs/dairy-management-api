package com.velanthralabs.dairy.features.purchase.service;

import com.velanthralabs.dairy.features.purchase.domain.MilkPurchase;
import com.velanthralabs.dairy.features.purchase.repository.MilkPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilkPurchaseService {

    private final MilkPurchaseRepository repository;

    @Transactional
    public MilkPurchase saveEntry(MilkPurchase entry) {
        // 1. Convert Kgs to Liters (Standard Dairy Formula: Liters = Kgs / Density)
        // Usually, density is derived from CLR. Example: 1.030
        BigDecimal density = new BigDecimal("1.030");
        BigDecimal liters = entry.getQtyKgs().divide(density, 2, RoundingMode.HALF_UP);
        entry.setQtyLiters(liters);

        // 2. Calculate Rate (Example: Base 40 + (Fat - 3.5) * 2)
        // You can replace this with your specific pricing chart logic
        BigDecimal baseRate = new BigDecimal("40.00");
        BigDecimal fatBonus = entry.getFat().subtract(new BigDecimal("3.5")).multiply(new BigDecimal("2.0"));
        BigDecimal finalRate = baseRate.add(fatBonus);

        entry.setRatePerLiter(finalRate);

        // 3. Calculate Total
        entry.setTotalAmount(liters.multiply(finalRate).setScale(2, RoundingMode.HALF_UP));

        return repository.save(entry);
    }
    // Add this method inside the class
    public List<MilkPurchase> getPurchasesByVendor(Long vendorId) {
        return repository.findByVendorId(vendorId);
    }
}