package com.tapusd.accountcommandproject.event;

import com.tapusd.accountcommandproject.domain.enamuration.Status;

public class AccountActivatedEvent extends BaseEvent<String>{
    public final Status status;

    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
