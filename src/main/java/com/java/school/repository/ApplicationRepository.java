package com.java.school.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.school.config.QueryConfig;
import com.java.school.domain.City;
import com.java.school.domain.PackageSize;
import com.java.school.domain.PackageType;
import com.java.school.domain.TransportType;
import com.java.school.domain.TransportVelocity;

@Repository
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
    private final QueryConfig queryConfig;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbcTemplate, QueryConfig queryConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryConfig = queryConfig;
    }

    public List<PackageSize> getSizes() {
        return jdbcTemplate.query(queryConfig.getPackageSize(), (rs, rowNum) ->
                PackageSize.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .priceFactor(rs.getInt(PRICE_FACTOR_COLUMN))
                        .build());
    }

    public List<PackageType> getPackageTypes() {
        return jdbcTemplate.query(queryConfig.getPackageType(), (rs, rowNum) ->
                PackageType.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .price(rs.getInt(PRICE_COLUMN))
                        .build());
    }

    public List<TransportVelocity> getTransportVelocity() {
        return jdbcTemplate.query(queryConfig.getTransportVelocity(), (rs, rowNum) ->
                TransportVelocity.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .priceFactor(rs.getInt(PRICE_FACTOR_COLUMN))
                        .build());
    }

    public List<TransportType> getTransportTypes() {
        return jdbcTemplate.query(queryConfig.getTransportType(), (rs, rowNum) ->
                TransportType.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .description(rs.getString(DESCRIPTION_COLUMN))
                        .pricePerMile(rs.getInt(PRICE_PER_MILE_COLUMN))
                        .build());
    }

    public List<City> getCities() {
        return jdbcTemplate.query(queryConfig.getCity(), (rs, rowNum) ->
                City.builder()
                        .id(rs.getInt(ID_COLUMN))
                        .name(rs.getString(NAME_COLUMN))
                        .tax(rs.getInt(TAX_COLUMN))
                        .seaport(rs.getBoolean(SEAPORT_COLUMN))
                        .airport(rs.getBoolean(AIRPORT_COLUMN))
                        .build());
    }

}
