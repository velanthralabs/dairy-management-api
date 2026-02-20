package com.velanthralabs.dairy.features.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.velanthralabs.dairy.core.entity.BaseEntity;
import com.velanthralabs.dairy.features.vendors.domain.Vendor;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vendor vendor;

    private LocalDate fromDate;
    private LocalDate toDate;

    private BigDecimal totalKgs;
    private BigDecimal totalAmount;
    private String invoiceNumber; // e.g., INV-2026-001
    private String status;
    private BigDecimal totalLiters; // You mentioned this was null
    private BigDecimal milkValue;   // Base price of milk
    private BigDecimal grossAmount; // milkValue + any bonuses
    private BigDecimal piTotal;     // Perhaps deductions or specific dairy tax?
    private BigDecimal netPayable;
}