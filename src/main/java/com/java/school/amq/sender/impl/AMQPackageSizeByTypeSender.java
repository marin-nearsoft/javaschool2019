package com.java.school.amq.sender.impl;

import com.java.school.amq.sender.AMQSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AMQPackageSizeByTypeSender extends AMQSender {

    private String packageTypeName;

    @Override
    public Object getFromRepo() {
        return applicationRepository.getPackageSizesByPackageType(packageTypeName);
    }

    public void setPackageType(String packageTypeName) {
        this.packageTypeName = packageTypeName;
    }
}