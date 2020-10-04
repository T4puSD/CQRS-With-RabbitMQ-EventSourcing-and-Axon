package com.tapusd.accountcommandproject.domain;

import com.tapusd.accountcommandproject.command.CreateAccountCommand;
import com.tapusd.accountcommandproject.command.CreditMoneyCommand;
import com.tapusd.accountcommandproject.command.DebitMoneyCommand;
import com.tapusd.accountcommandproject.domain.enamuration.Status;
import com.tapusd.accountcommandproject.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private Double accountBalance;
    private String currency;
    private String status;

    public AccountAggregate(){

    }

    @CommandHandler
    public AccountAggregate (CreateAccountCommand createAccountCommand){
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id,
                createAccountCommand.accountBalance,
                createAccountCommand.currency));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id,Status.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent){
        this.status = String.valueOf(accountActivatedEvent.status);
    }

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id,creditMoneyCommand.creditAmount,creditMoneyCommand.currency));

    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent){

        if (this.accountBalance < 0 & (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0){
            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
        }

        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @CommandHandler
    protected void on(DebitMoneyCommand debitMoneyCommand){
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.id,debitMoneyCommand.debitAmount,debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(MoneyDebitedEvent moneyDebitedEvent){
        if(this.status.equals(Status.HOLD.toString())){
            AggregateLifecycle.apply(new AccountHeldEvent(moneyDebitedEvent.id,Status.HOLD));
            return;
        }
        if(this.accountBalance >=0 & (this.accountBalance - moneyDebitedEvent.debitAmount) <0){
            AggregateLifecycle.apply(new AccountHeldEvent(moneyDebitedEvent.id,Status.HOLD));
        }
        this.accountBalance -= moneyDebitedEvent.debitAmount;
    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent accountHeldEvent){
        this.status = String.valueOf(accountHeldEvent.status);
    }

    public String getId() {
        return id;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AccountAggregate{" +
                "id='" + id + '\'' +
                ", accountBalance=" + accountBalance +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
