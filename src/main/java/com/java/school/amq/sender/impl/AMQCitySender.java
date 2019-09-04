package com.java.school.amq.sender.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.City;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQCitySender implements AMQSender<City> {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public AMQCitySender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<City> get() {
        logger.info("Calling getCities from repo");
        return applicationRepository.getCities();
    }
}
