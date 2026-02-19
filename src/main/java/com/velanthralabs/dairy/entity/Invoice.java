package com.velanthralabs.dairy.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double totalKgs;

    private Double totalLiters;

    private Double milkValue;

    private Double piTotal;

    private Double grossAmount;

    private Double netPayable;

    private LocalDateTime generatedAt = LocalDateTime.now();
}
