package com.java.school.amq.sender;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AMQSender {
    String get() throws JsonProcessingException;
}
