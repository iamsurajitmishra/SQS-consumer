package com.sqs.mq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.mq.model.Employee;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class SQSConsumerController {
   private static final Logger logger = LoggerFactory.getLogger(SQSConsumerController.class);
   private ObjectMapper objectMapper;
    /*
       Providing sqs queue name to the listner
     */

    @PostConstruct
    public void init(){
        objectMapper = new ObjectMapper();
    }


    @SqsListener("test-sqs")
    public void loadMessagesFromQ(String json) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(json,Employee.class);
      logger.info("Message received: "+employee.getName());
      logger.info("Message received: "+employee.getSalary());
      logger.info("Message received:"+employee.getAddress().getCity());
      logger.info("Message received:"+employee.getAddress().getState());
      logger.info("Message received:"+employee.getAddress().getCountry());
    }

}
