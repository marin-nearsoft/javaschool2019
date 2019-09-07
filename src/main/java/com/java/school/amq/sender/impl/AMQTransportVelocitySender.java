package com.java.school.amq.sender.impl;

import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AMQTransportVelocitySender extends AMQSender {

    @Override
    public Object getFromRepo() {
        return applicationRepository.getTransportVelocity();
    }
}
