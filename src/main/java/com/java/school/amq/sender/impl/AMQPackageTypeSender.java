package com.java.school.amq.sender.impl;

import java.util.List;
import java.util.Objects;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.PackageType;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQPackageTypeSender implements AMQSender<PackageType> {

    private ApplicationRepository applicationRepository;
    private static AMQPackageTypeSender instance = null;

    private AMQPackageTypeSender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public static AMQPackageTypeSender getInstance(ApplicationRepository applicationRepository) {
        if (Objects.isNull(instance)) {
            instance = new AMQPackageTypeSender(applicationRepository);
        }
        return instance;
    }

    @Override
    public List<PackageType> get() {
        logger.info("Calling getPackageTypes from repo");
        return applicationRepository.getPackageTypes();
    }
}
