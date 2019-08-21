package com.example.demo.stub.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringRepo {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public SpringRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getSizes(){
        return jdbcTemplate.queryForList("SELECT description FROM package_size;", String.class);
    }

    public List<String> getTypes(){
        return jdbcTemplate.queryForList("SELECT description FROM package_type;", String.class);
    }

    public List<String> getVelocity(){
        return jdbcTemplate.queryForList("SELECT description FROM transport_velocity;", String.class);
    }

    public List<String> getTransport(){
        return jdbcTemplate.queryForList("SELECT description FROM transport_type;", String.class);
    }

    public List<String> getCities() {
        return jdbcTemplate.queryForList("SELECT name FROM city;", String.class);
    }
}
