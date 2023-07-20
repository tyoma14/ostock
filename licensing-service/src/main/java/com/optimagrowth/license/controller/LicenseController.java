package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Artyom Zheltyshev on 09.07.2023
 */
@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping(value="/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        License license = licenseService.getLicense(organizationId, licenseId);
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License license) {
        return ResponseEntity.ok(licenseService.createLicense(license));
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(@RequestBody License license) {
        return ResponseEntity.ok(licenseService.updateLicense(license));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }

}
