package com.velanthralabs.dairy.vendors.domain;


import com.velanthralabs.dairy.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "vendors", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    private String phone;

    private String bankAccount;

    private String bankName;

    private String centerCode;

    private String route;
}
