package com.optimagrowth.license.service.client;

import com.optimagrowth.license.config.OrganizationRedisRepository;
import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.utils.UserContext;
import com.optimagrowth.license.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by Artyom Zheltyshev on 30.07.2023
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrganizationRestTemplateClient {

    private final RestTemplate restTemplate;
    private final OrganizationRedisRepository redisRepository;

    public Organization getOrganization(String organizationId) {
        log.debug("In Licensing Service.getOrganization: {}", UserContextHolder.getContext().getCorrelationId());
        return checkRedisCache(organizationId)
                .orElseGet(() -> {
                    log.debug("Unable to locate organization from the redis cache: {}.", organizationId);
                    String url = "http://organization-service/v1/organization/{organizationId}";
                    Organization organization = restTemplate.getForObject(url, Organization.class, organizationId);
                    if (organization != null) {
                        cacheOrganizationObject(organization);
                    }
                    return organization;
                });
    }

    private Optional<Organization> checkRedisCache(String organizationId) {
        try {
            return redisRepository.findById(organizationId);
        } catch (Exception e) {
            log.error("Error encountered while trying to retrieve " +
                    "organization {} check Redis Cache. Exception {}", organizationId, e);
            return Optional.empty();
        }
    }

    private void cacheOrganizationObject(Organization organization) {
        try {
            redisRepository.save(organization);
        } catch (Exception e) {
            log.error("Unable to cache organization {} in Redis. Exception {}", organization.getId(), e);
        }
    }

}
