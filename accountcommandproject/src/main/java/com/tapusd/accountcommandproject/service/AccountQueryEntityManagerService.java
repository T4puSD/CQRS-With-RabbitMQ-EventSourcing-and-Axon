package com.tapusd.accountcommandproject.service;

import com.tapusd.accountcommandproject.domain.AccountAggregate;
import com.tapusd.accountcommandproject.event.BaseEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AccountQueryEntityManagerService {

    @Value("${tsd.rabbitmq.exchange}")
    String exchange;

    @Value("${tsd.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    @Qualifier(value = "modifiedRabbitTemplate")
    AmqpTemplate amqpTemplate;

//    @Autowired
//    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("accountAggregateEventSourcingRepository")
    private EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository;

    @EventSourcingHandler
    void on(BaseEvent event){
        AccountAggregate aggregate = getAccountFromEvent(event);
//        System.out.println(aggregate);
        amqpTemplate.convertAndSend(exchange,routingkey,aggregate);
    }


    private AccountAggregate getAccountFromEvent(BaseEvent event){
        return accountAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }
}
