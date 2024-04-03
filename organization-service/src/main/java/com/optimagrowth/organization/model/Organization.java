package com.optimagrowth.organization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Artyom Zheltyshev on 31.03.2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organization {

    @Id
    private String id;

    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

}
