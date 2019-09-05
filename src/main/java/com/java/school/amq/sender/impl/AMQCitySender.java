package com.java.school.amq.sender.impl;

import java.util.List;
import java.util.Objects;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.City;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQCitySender implements AMQSender<City> {

    private ApplicationRepository applicationRepository;
    private static AMQCitySender instance = null;

    private AMQCitySender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public static AMQCitySender getInstance(ApplicationRepository applicationRepository) {
        if (Objects.isNull(instance)) {
            instance = new AMQCitySender(applicationRepository);
        }
        return instance;
    }

    @Override
    public List<City> get() {
        logger.info("Calling getCities from repo");
        return applicationRepository.getCities();
    }
}
