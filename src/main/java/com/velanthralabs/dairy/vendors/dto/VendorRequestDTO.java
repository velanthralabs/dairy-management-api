package com.velanthralabs.dairy.vendors.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorRequestDTO {

    private String name;
    private String phone;
    private String bankAccount;
    private String bankName;
    private String centerCode;
    private String route;
}
