package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Artyom Zheltyshev on 18.07.2023
 */
@Repository
public interface LicenseRepository extends CrudRepository<License, String> {

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

}
