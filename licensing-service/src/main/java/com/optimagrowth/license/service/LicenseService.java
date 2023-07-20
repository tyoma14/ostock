package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Artyom Zheltyshev on 09.07.2023
 */
@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final ServiceConfig config;

    public License getLicense(String organizationId, String licenseId){
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            throw new IllegalArgumentException("License not found");
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        String responseMessage = null;
        licenseRepository.deleteById(licenseId);
        responseMessage = String.format("Deleting license with id %s", licenseId);
        return responseMessage;
    }

}
