package com.tapusd.accountcommandproject.service;

import com.tapusd.accountcommandproject.dto.AccountCreateDTO;
import com.tapusd.accountcommandproject.dto.MoneyCreditDTO;
import com.tapusd.accountcommandproject.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);

}
