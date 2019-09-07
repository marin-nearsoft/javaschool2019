package com.java.school.amq.factory;

import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;
import com.java.school.amq.sender.impl.AMQCitySender;
import com.java.school.amq.sender.impl.AMQPackageSizeSender;
import com.java.school.amq.sender.impl.AMQPackageTypeSender;
import com.java.school.amq.sender.impl.AMQTransportTypeSender;
import com.java.school.amq.sender.impl.AMQTransportVelocitySender;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQFactory {

    private AMQPackageSizeSender amqPackageSizeSender;
    private AMQPackageTypeSender amqPackageTypeSender;
    private AMQTransportVelocitySender amqTransportVelocitySender;
    private AMQTransportTypeSender amqTransportTypeSender;
    private AMQCitySender amqCitySender;

    public void setAmqPackageSizeSender(AMQPackageSizeSender amqPackageSizeSender) {
        this.amqPackageSizeSender = amqPackageSizeSender;
    }

    public void setAmqPackageTypeSender(AMQPackageTypeSender amqPackageTypeSender) {
        this.amqPackageTypeSender = amqPackageTypeSender;
    }

    public void setAmqTransportVelocitySender(AMQTransportVelocitySender amqTransportVelocitySender) {
        this.amqTransportVelocitySender = amqTransportVelocitySender;
    }

    public void setAmqTransportTypeSender(AMQTransportTypeSender amqTransportTypeSender) {
        this.amqTransportTypeSender = amqTransportTypeSender;
    }

    public void setAmqCitySender(AMQCitySender amqCitySender) {
        this.amqCitySender = amqCitySender;
    }

    public AMQSender getSender(String tableName) {
        logger.info("Searching [{}] table", tableName);
        switch (tableName) {
            case "packageSize":
                return amqPackageSizeSender;
            case "packageType":
                return amqPackageTypeSender;
            case "transportVelocity":
                return amqTransportVelocitySender;
            case "transportType":
                return amqTransportTypeSender;
            case "city":
                return amqCitySender;
            default:
                throw new IllegalArgumentException("There is no table with that name");
        }
    }
}
