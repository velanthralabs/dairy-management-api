package com.velanthralabs.dairy.features.vendors.repository;

import com.velanthralabs.dairy.features.vendors.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorManagementRepository extends JpaRepository<Vendor, Long> {
}
