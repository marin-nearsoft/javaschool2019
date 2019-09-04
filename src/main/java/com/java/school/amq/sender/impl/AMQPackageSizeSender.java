package com.java.school.amq.sender.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.PackageSize;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQPackageSizeSender implements AMQSender<PackageSize> {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public AMQPackageSizeSender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<PackageSize> get() {
        logger.info("Calling getPackageSizes from repo");
        return applicationRepository.getPackageSizes();
    }
}
