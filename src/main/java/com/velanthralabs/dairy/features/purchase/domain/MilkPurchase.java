package com.velanthralabs.dairy.features.purchase.domain;

import com.velanthralabs.dairy.core.entity.BaseEntity;
import com.velanthralabs.dairy.core.enums.SessionType;
import com.velanthralabs.dairy.features.vendors.domain.Vendor;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "milk_purchases")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MilkPurchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    private LocalDate entryDate;
    private SessionType session; // "AM" or "PM"

    // Raw Inputs
    private BigDecimal qtyKgs;
    private BigDecimal fat;
    private BigDecimal snf;
    private BigDecimal clr; // Often used in Indian dairy for density

    // Calculated Fields (Calculated in Service)
    private BigDecimal qtyLiters;
    private BigDecimal ratePerLiter;
    private BigDecimal totalAmount;

    @Column(name = "is_invoiced")
    private boolean invoiced = false; // Important: Prevents double-billing
}