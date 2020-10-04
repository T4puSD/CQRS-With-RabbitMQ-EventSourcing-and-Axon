package com.tapusd.accountqueryproject.controller;

import com.tapusd.accountqueryproject.domain.AccountQueryEntity;
import com.tapusd.accountqueryproject.service.AccountQueryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bank-accounts")
@Api(value = "Account Queries",description = "Account Query Endpoint", tags = "Account Queries")
public class AccountQueryController {
    private final AccountQueryService accountQueryService;

    @Autowired
    public AccountQueryController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping(value = "/{accountNumber}")
    public AccountQueryEntity getAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return accountQueryService.getAccount(accountNumber);
    }

//    @GetMapping(value = "/{accountNumber}/events")
//    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber){
//        return accountQueryService.listEventsForAccount(accountNumber);
//    }
}
