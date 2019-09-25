package com.java.school.amq.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.java.school.amq.sender.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQFactory {

    private AMQPackageSizeByTypeSender amqPackageSizeByTypeSender;
    private AMQPackageSizeSender amqPackageSizeSender;
    private AMQPackageTypeSender amqPackageTypeSender;
    private AMQTransportVelocitySender amqTransportVelocitySender;
    private AMQTransportTypeSender amqTransportTypeSender;
    private AMQCitySender amqCitySender;
    private AMQRoutesSender amqRoutesSender;

    public AMQFactory(AMQPackageSizeByTypeSender amqPackageSizeByTypeSender,
                      AMQPackageSizeSender amqPackageSizeSender,
                      AMQPackageTypeSender amqPackageTypeSender,
                      AMQTransportVelocitySender amqTransportVelocitySender,
                      AMQTransportTypeSender amqTransportTypeSender,
                      AMQCitySender amqCitySender,
                      AMQRoutesSender amqRoutesSender) {
        this.amqPackageSizeByTypeSender = amqPackageSizeByTypeSender;
        this.amqPackageSizeSender = amqPackageSizeSender;
        this.amqPackageTypeSender = amqPackageTypeSender;
        this.amqTransportVelocitySender = amqTransportVelocitySender;
        this.amqTransportTypeSender = amqTransportTypeSender;
        this.amqCitySender = amqCitySender;
        this.amqRoutesSender = amqRoutesSender;
    }

    public AMQSender getSender(JsonNode jsonNode) {
        String tableName = jsonNode.get("type").asText();
        logger.info("Searching [{}] table", tableName);
        switch (tableName) {
            case "packageSizeByType":
                String packageType = jsonNode.get("packageType").asText();
                amqPackageSizeByTypeSender.setPackageType(packageType);
                return amqPackageSizeByTypeSender;
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
            case "routesList":
                amqRoutesSender.setCityOrigin(jsonNode.get("origin").asText());
                amqRoutesSender.setCityDestination(jsonNode.get("destination").asText());
                return amqRoutesSender;
            default:
                throw new IllegalArgumentException("There is no table with that name");
        }
    }
}
