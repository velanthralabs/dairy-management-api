package com.velanthralabs.dairy.vendors.service.impl;

import com.velanthralabs.dairy.vendors.domain.Vendor;
import com.velanthralabs.dairy.vendors.dto.VendorRequestDTO;
import com.velanthralabs.dairy.vendors.dto.VendorResponseDTO;
import com.velanthralabs.dairy.vendors.exception.VendorNotFoundException;
import com.velanthralabs.dairy.vendors.repository.VendorManagementRepository;
import com.velanthralabs.dairy.vendors.service.VendorManagementService;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendorManagementServiceImpl implements VendorManagementService {

    private static final Logger log =
            LoggerFactory.getLogger(VendorManagementServiceImpl.class);

    private final VendorManagementRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<VendorResponseDTO> findAllVendors() {
        log.info("Fetching all vendors");

        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VendorResponseDTO getVendorById(Long id) {
        Vendor vendor = repository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(id));

        return mapToResponseDTO(vendor);
    }

    @Override
    public void createVendor(VendorRequestDTO request) {
        log.info("Creating vendor {}", request.getName());

        Vendor vendor = Vendor.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .bankAccount(request.getBankAccount())
                .bankName(request.getBankName())
                .centerCode(request.getCenterCode())
                .route(request.getRoute())
                .build();

        repository.save(vendor);
    }

    @Override
    public void updateVendor(Long id, VendorRequestDTO request) {

        Vendor vendor = repository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(id));

        vendor.setName(request.getName());
        vendor.setPhone(request.getPhone());
        vendor.setBankAccount(request.getBankAccount());
        vendor.setBankName(request.getBankName());
        vendor.setCenterCode(request.getCenterCode());
        vendor.setRoute(request.getRoute());
    }

    @Override
    public void deleteVendor(Long id) {

        if (!repository.existsById(id)) {
            throw new VendorNotFoundException(id);
        }

        repository.deleteById(id);
    }

    private VendorResponseDTO mapToResponseDTO(Vendor vendor) {
        return VendorResponseDTO.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .bankAccount(vendor.getBankAccount())
                .bankName(vendor.getBankName())
                .centerCode(vendor.getCenterCode())
                .route(vendor.getRoute())
                .build();
    }
}
