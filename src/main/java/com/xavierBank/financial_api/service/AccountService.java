package com.xavierBank.financial_api.service;

import com.xavierBank.financial_api.dto.AccountRequest;
import com.xavierBank.financial_api.entity.Account;
import com.xavierBank.financial_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account createAccount(AccountRequest request){

        Account account = new Account();

        account.setAccountId(UUID.randomUUID());
        account.setCustomerName(request.getCustomerName());
        account.setDocumentNumber(request.getDocumentNumber());
        account.setEmail(request.getEmail());

        account.setActive(true);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);

    }


}
