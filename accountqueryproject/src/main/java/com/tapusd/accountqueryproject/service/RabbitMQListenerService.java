package com.tapusd.accountqueryproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapusd.accountqueryproject.domain.AccountQueryEntity;
import com.tapusd.accountqueryproject.repository.AccountRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {

    @Autowired
    private AccountRepository accountRepository;

    @RabbitListener(queues = "${tsd.rabbitmq.queue}")
    public void listen(String in) throws JsonProcessingException {
        System.out.println(in);
        ObjectMapper mapper = new ObjectMapper();
        AccountQueryEntity accountQueryEntity = mapper.readValue(in,AccountQueryEntity.class);
        accountRepository.save(accountQueryEntity);
    }

}
