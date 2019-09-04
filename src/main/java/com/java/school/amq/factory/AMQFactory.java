package com.java.school.amq.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.school.amq.sender.AMQSender;
import com.java.school.amq.sender.impl.AMQCitySender;
import com.java.school.amq.sender.impl.AMQPackageSizeSender;
import com.java.school.amq.sender.impl.AMQPackageTypeSender;
import com.java.school.amq.sender.impl.AMQTransportTypeSender;
import com.java.school.amq.sender.impl.AMQTransportVelocitySender;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AMQFactory {

    private final AMQPackageSizeSender amqPackageSizeSender;
    private final AMQPackageTypeSender amqPackageTypeSender;
    private final AMQTransportVelocitySender amqTransportVelocitySender;
    private final AMQTransportTypeSender amqTransportTypeSender;
    private final AMQCitySender amqCitySender;

    @Autowired
    public AMQFactory(AMQPackageSizeSender amqPackageSizeSender, AMQPackageTypeSender amqPackageTypeSender,
                      AMQTransportVelocitySender amqTransportVelocitySender, AMQTransportTypeSender amqTransportTypeSender,
                      AMQCitySender amqCitySender) {
        this.amqPackageSizeSender = amqPackageSizeSender;
        this.amqPackageTypeSender = amqPackageTypeSender;
        this.amqTransportVelocitySender = amqTransportVelocitySender;
        this.amqTransportTypeSender = amqTransportTypeSender;
        this.amqCitySender = amqCitySender;
    }

    public AMQSender getSender(String tableName) {
        logger.info("Searching [{}] table", tableName);
        switch (tableName) {
            case "package_size":
                return amqPackageSizeSender;
            case "package_type":
                return amqPackageTypeSender;
            case "transport_velocity":
                return amqTransportVelocitySender;
            case "transport_type":
                return amqTransportTypeSender;
            case "city":
                return amqCitySender;
            default:
                throw new IllegalArgumentException("There is no table with that name");
        }
    }
}
