package com.optimagrowth.license.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Artyom Zheltyshev on 09.07.2023
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "licenses")
public class License {

    @Id
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
    private String comment;

    public License withComment(String comment) {
        this.setComment(comment);
        return this;
    }

}
