package com.optimagrowth.organization.repository;

import com.optimagrowth.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Artyom Zheltyshev on 31.03.2024
 */
public interface OrganizationRepository extends CrudRepository<Organization, String> {
}
