package com.velanthralabs.dairy.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String phone;

    private String bankAccount;

    private String bankName;

    private String centerCode;

    private String route;
}
