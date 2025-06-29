package com.optimagrowth.license.model;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Getter @Setter @ToString
@EqualsAndHashCode
@RedisHash("organization")
public class Organization {

    @Id
	String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
    
}
