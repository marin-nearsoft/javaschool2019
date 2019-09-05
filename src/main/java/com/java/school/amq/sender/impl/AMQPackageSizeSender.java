package com.java.school.amq.sender.impl;

import java.util.List;
import java.util.Objects;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.PackageSize;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQPackageSizeSender implements AMQSender<PackageSize> {

    private ApplicationRepository applicationRepository;
    private static AMQPackageSizeSender instance = null;

    private AMQPackageSizeSender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public static AMQPackageSizeSender getInstance(ApplicationRepository applicationRepository) {
        if (Objects.isNull(instance)) {
            instance = new AMQPackageSizeSender(applicationRepository);
        }
        return instance;
    }

    @Override
    public List<PackageSize> get() {
        logger.info("Calling getPackageSizes from repo");
        return applicationRepository.getPackageSizes();
    }
}
