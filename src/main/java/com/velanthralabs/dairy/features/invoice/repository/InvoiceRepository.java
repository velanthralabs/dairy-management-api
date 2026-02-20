package com.velanthralabs.dairy.features.invoice.repository;

import com.velanthralabs.dairy.features.invoice.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Allows Admin to see all previous invoices for a specific vendor
    List<Invoice> findByVendorIdOrderByFromDateDesc(Long vendorId);
}