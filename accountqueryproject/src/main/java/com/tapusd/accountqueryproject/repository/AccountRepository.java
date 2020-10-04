package com.tapusd.accountqueryproject.repository;


import com.tapusd.accountqueryproject.domain.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountQueryEntity,String> {

}
