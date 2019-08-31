package com.example.demo.stub.config;

import com.example.demo.stub.repository.SpringRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AMQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(AMQReceiver.class);

    @Autowired
    private SpringRepo repository;

    public List<String> receiveMessage(String tableName) {
        List<String> list = null;

        logger.info("Received <" + tableName + ">");

        if (tableName.equals("package_type")) {
            list = repository.getTypes();
            logger.info("Response <" + list.toString() + ">");
        }

        return list;
    }

}