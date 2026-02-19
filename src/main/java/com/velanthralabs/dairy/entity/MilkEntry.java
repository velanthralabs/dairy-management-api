package com.velanthralabs.dairy.entity;

import com.velanthralabs.dairy.entity.enums.SessionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Table(name = "milk_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilkEntry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private SessionType session;

    private Double kgs;
    private Double liters;
    private Integer cans;
    private Double fat;
    private Double snf;

    private Double ratePerLiter;
    private Double netAmount;
    private Double piExpense;
    private Double totalAmount;

    private Boolean invoiceGenerated = false;
}
