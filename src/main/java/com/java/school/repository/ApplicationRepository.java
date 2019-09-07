package com.java.school.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.school.config.QueryConfigurationProperties;
import com.java.school.domain.City;
import com.java.school.domain.PackageSize;
import com.java.school.domain.PackageType;
import com.java.school.domain.TransportType;
import com.java.school.domain.TransportVelocity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ApplicationRepository {

    private static final String ID_COLUMN = "id";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String PRICE_FACTOR_COLUMN = "price_factor";
    private static final String PRICE_COLUMN = "price";
    private static final String PRICE_PER_MILE_COLUMN = "price_per_mile";
    private static final String NAME_COLUMN = "name";
    private static final String TAX_COLUMN = "tax";
    private static final String SEAPORT_COLUMN = "seaport";
    private static final String AIRPORT_COLUMN = "airport";

    private final JdbcTemplate jdbcTemplate;
    private final QueryConfigurationProperties queryConfigurationProperties;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbcTemplate, QueryConfigurationProperties queryConfigurationProperties) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryConfigurationProperties = queryConfigurationProperties;
    }

    public List<PackageSize> getPackageSizes() {
        logger.info("Getting all packages sizes from the database");
        return jdbcTemplate.query(queryConfigurationProperties.getPackageSize(), (rs, rowNum) ->
                PackageSize.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .priceFactor(rs.getInt(PRICE_FACTOR_COLUMN))
                        .build());
    }

    public List<PackageType> getPackageTypes() {
        logger.info("Getting all packages types from the database");
        return jdbcTemplate.query(queryConfigurationProperties.getPackageType(), (rs, rowNum) ->
                PackageType.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .price(rs.getInt(PRICE_COLUMN))
                        .build());
    }

    public List<TransportVelocity> getTransportVelocity() {
        logger.info("Getting all transport velocities from the database");
        return jdbcTemplate.query(queryConfigurationProperties.getTransportVelocity(), (rs, rowNum) ->
                TransportVelocity.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .priceFactor(rs.getInt(PRICE_FACTOR_COLUMN))
                        .build());
    }

    public List<TransportType> getTransportTypes() {
        logger.info("Getting all transport types from the database");
        return jdbcTemplate.query(queryConfigurationProperties.getTransportType(), (rs, rowNum) ->
                TransportType.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .pricePerMile(rs.getInt(PRICE_PER_MILE_COLUMN))
                        .build());
    }

    public List<City> getCities() {
        logger.info("Getting all cities from the database");
        return jdbcTemplate.query(queryConfigurationProperties.getCity(), (rs, rowNum) ->
                City.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .name(rs.getString(NAME_COLUMN))
                        .tax(rs.getInt(TAX_COLUMN))
                        .seaport(rs.getBoolean(SEAPORT_COLUMN))
                        .airport(rs.getBoolean(AIRPORT_COLUMN))
                        .build());
    }

    public List<PackageSize> getPackageSizesByPackageType(String packateTypeName) {
        // TODO Ricardo do your magic
        // Creo que seria el siguiente query man
        // SELECT * FROM package_size WHERE id in (SELECT package_size_id FROM package_type_size WHERE package_type_id = (SELECT id FROM package_type WHERE description = 'Envelope'))
        return null;
    }
}
