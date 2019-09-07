package com.java.school.amq.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public abstract class AMQSender {

    protected ApplicationRepository applicationRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public void setApplicationRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String get() throws JsonProcessingException {
        logger.info("Calling get object from repo");
        return objectMapper.writeValueAsString(getFromRepo());
    }

    public abstract Object getFromRepo();
}
