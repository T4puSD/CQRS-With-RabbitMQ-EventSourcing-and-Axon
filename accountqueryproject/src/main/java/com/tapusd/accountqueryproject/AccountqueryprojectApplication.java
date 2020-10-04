package com.tapusd.accountqueryproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rabbitmq.tools.json.JSONUtil;
import com.tapusd.accountqueryproject.domain.AccountQueryEntity;
import com.tapusd.accountqueryproject.repository.AccountRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountqueryprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountqueryprojectApplication.class, args);
    }
}
