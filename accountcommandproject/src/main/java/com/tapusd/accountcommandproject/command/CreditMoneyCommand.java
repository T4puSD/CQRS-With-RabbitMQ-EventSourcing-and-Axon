package com.tapusd.accountcommandproject.command;

public class CreditMoneyCommand extends BaseCommand<String>{
    public final Double creditAmount;
    public final String currency;

    public CreditMoneyCommand(String id, Double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
