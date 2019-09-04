package com.java.school.amq.sender.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.PackageType;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQPackageTypeSender implements AMQSender<PackageType> {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public AMQPackageTypeSender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<PackageType> get() {
        logger.info("Calling getPackageTypes from repo");
        return applicationRepository.getPackageTypes();
    }
}
