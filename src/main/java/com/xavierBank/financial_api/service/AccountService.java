package com.xavierBank.financial_api.service;

import com.xavierBank.financial_api.dto.AccountRequest;
import com.xavierBank.financial_api.dto.TransactionRequest;
import com.xavierBank.financial_api.entity.Account;
import com.xavierBank.financial_api.entity.AccountBalance;
import com.xavierBank.financial_api.entity.Transaction;
import com.xavierBank.financial_api.entity.TransactionType;
import com.xavierBank.financial_api.repository.AccountRepository;
import com.xavierBank.financial_api.repository.BalanceRepository;
import com.xavierBank.financial_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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

    public Transaction createTransaction(UUID accountId, TransactionRequest request) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(LocalDateTime.now());
        transaction.setTransactionType(
                TransactionType.valueOf(request.getTransactionType().toUpperCase())
        );

        transactionRepository.save(transaction);

        updateBalance(accountId, request);

        return transaction;
    }


    private void updateBalance(UUID accountId, TransactionRequest request){

        AccountBalance balance = balanceRepository.findById(accountId)
                .orElseGet(() -> {
                    AccountBalance b = new AccountBalance();
                    b.setAccountId(accountId);
                    b.setSaldo(BigDecimal.ZERO);
                    return b;
                });
        if (TransactionType.valueOf(request.getTransactionType().toUpperCase()) == TransactionType.CREDIT) {
            balance.setSaldo(balance.getSaldo().add(request.getAmount()));
        } else {
            balance.setSaldo(balance.getSaldo().subtract(request.getAmount()));
        }
        balanceRepository.save(balance);
    }
}
