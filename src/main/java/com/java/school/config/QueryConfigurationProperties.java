package com.java.school.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "java.school.query")
@Data
public class QueryConfigurationProperties {

    private String packageSize;
    private String packageType;
    private String transportVelocity;
    private String transportType;
    private String city;
    private String packageSizeByPackageType;

}
