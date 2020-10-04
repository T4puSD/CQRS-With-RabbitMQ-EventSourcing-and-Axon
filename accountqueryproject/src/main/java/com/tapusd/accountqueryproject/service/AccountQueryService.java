package com.tapusd.accountqueryproject.service;


import com.tapusd.accountqueryproject.domain.AccountQueryEntity;

import java.util.List;

public interface AccountQueryService {
    public AccountQueryEntity getAccount(String accountNumber);
}
