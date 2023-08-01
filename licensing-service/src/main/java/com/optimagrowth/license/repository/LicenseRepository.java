package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 18.07.2023
 */
@Repository
public interface LicenseRepository extends CrudRepository<License, String> {

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

    List<License> findByOrganizationId(String organizationId);

}
