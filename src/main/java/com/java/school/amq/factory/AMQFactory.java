package com.java.school.amq.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.school.amq.sender.AMQSender;
import com.java.school.amq.sender.impl.AMQCitySender;
import com.java.school.amq.sender.impl.AMQPackageSizeSender;
import com.java.school.amq.sender.impl.AMQPackageTypeSender;
import com.java.school.amq.sender.impl.AMQTransportTypeSender;
import com.java.school.amq.sender.impl.AMQTransportVelocitySender;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQFactory {

    private final ApplicationRepository applicationRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AMQFactory(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        this.applicationRepository = applicationRepository;
        this.objectMapper = objectMapper;
    }

    public AMQSender getSender(String tableName) {
        logger.info("Searching [{}] table", tableName);
        switch (tableName) {
            case "packageSize":
                return AMQPackageSizeSender.getInstance(applicationRepository, objectMapper);
            case "packageType":
                return AMQPackageTypeSender.getInstance(applicationRepository, objectMapper);
            case "transportVelocity":
                return AMQTransportVelocitySender.getInstance(applicationRepository, objectMapper);
            case "transportType":
                return AMQTransportTypeSender.getInstance(applicationRepository, objectMapper);
            case "city":
                return AMQCitySender.getInstance(applicationRepository, objectMapper);
            default:
                throw new IllegalArgumentException("There is no table with that name");
        }
    }
}
