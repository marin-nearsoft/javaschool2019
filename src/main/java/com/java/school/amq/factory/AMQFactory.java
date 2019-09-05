package com.java.school.amq.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    public AMQFactory(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public AMQSender getSender(String tableName) {
        logger.info("Searching [{}] table", tableName);
        switch (tableName) {
            case "package_size":
                return AMQPackageSizeSender.getInstance(applicationRepository);
            case "package_type":
                return AMQPackageTypeSender.getInstance(applicationRepository);
            case "transport_velocity":
                return AMQTransportVelocitySender.getInstance(applicationRepository);
            case "transport_type":
                return AMQTransportTypeSender.getInstance(applicationRepository);
            case "city":
                return AMQCitySender.getInstance(applicationRepository);
            default:
                throw new IllegalArgumentException("There is no table with that name");
        }
    }
}
