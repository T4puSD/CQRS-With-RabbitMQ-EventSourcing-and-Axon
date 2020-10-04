package com.tapusd.accountcommandproject.event;

import com.tapusd.accountcommandproject.domain.enamuration.Status;

public class AccountHeldEvent extends BaseEvent<String>{
    public final Status status;

    public AccountHeldEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
