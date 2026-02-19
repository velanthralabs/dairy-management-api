package com.velanthralabs.dairy.vendors.service.impl;

import com.velanthralabs.dairy.vendors.domain.Vendor;
import com.velanthralabs.dairy.vendors.repository.VendorManagementRepository;
import com.velanthralabs.dairy.vendors.service.VendorManagementService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorManagementServiceImpl implements VendorManagementService {

    private final Logger log = LoggerFactory.getLogger(VendorManagementServiceImpl.class);
    private final VendorManagementRepository vendorManagementRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vendor> findAllVendors() {
        log.info("Finding all vendors");
        return vendorManagementRepository.findAll();
    }

    @Override
    public void createVendor(String name, String contactInfo) {

    }

    @Override
    public void updateVendor(Long vendorId, String name, String contactInfo) {

    }

    @Override
    public void deleteVendor(Long vendorId) {

    }
}
