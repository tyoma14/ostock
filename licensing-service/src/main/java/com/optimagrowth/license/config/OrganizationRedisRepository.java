package com.optimagrowth.license.config;

import com.optimagrowth.license.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Artyom Zheltyshev on 28.06.2025
 */
@Repository
public interface OrganizationRedisRepository extends CrudRepository<Organization, String> {

}
