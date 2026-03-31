package com.xavierBank.financial_api.service;

import com.xavierBank.financial_api.dto.AccountRequest;
import com.xavierBank.financial_api.entity.Account;
import com.xavierBank.financial_api.repository.AccountRepository;
import com.xavierBank.financial_api.repository.BalanceRepository;
import com.xavierBank.financial_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(AccountRequest request){

        Account account = new Account();

        account.setCustomerName(request.getCustomerName());
        account.setDocumentNumber(request.getDocumentNumber());
        account.setEmail(request.getEmail());

        account.setActive(true);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    public Account update(UUID id, AccountRequest request) {

        Account account = getById(id);

        account.setCustomerName(request.getCustomerName());
        account.setDocumentNumber(request.getDocumentNumber());
        account.setEmail(request.getEmail());
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    public void delete(UUID id) {

        Account account = getById(id);
        account.setActive(false);
        account.setUpdatedAt(LocalDateTime.now());

        repository.save(account);
    }


}
