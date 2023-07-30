package com.optimagrowth.license.service.client;

import com.optimagrowth.license.model.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Artyom Zheltyshev on 30.07.2023
 */
@Component
@RequiredArgsConstructor
public class OrganizationRestTemplateClient {

    private final RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        String url = "http://organization-service/v1/organization/{organizationId}";
        return restTemplate.getForObject(url, Organization.class, organizationId);
    }

}
