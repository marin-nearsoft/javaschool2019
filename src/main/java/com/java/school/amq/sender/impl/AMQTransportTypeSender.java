package com.java.school.amq.sender.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.TransportType;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQTransportTypeSender implements AMQSender<TransportType> {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public AMQTransportTypeSender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<TransportType> get() {
        logger.info("Calling getTransportTypes from repo");
        return applicationRepository.getTransportTypes();
    }
}
