package com.xavierBank.financial_api.controller;

import com.xavierBank.financial_api.dto.AccountRequest;
import com.xavierBank.financial_api.dto.TransactionRequest;
import com.xavierBank.financial_api.entity.Account;
import com.xavierBank.financial_api.entity.AccountBalance;
import com.xavierBank.financial_api.entity.Transaction;
import com.xavierBank.financial_api.repository.BalanceRepository;
import com.xavierBank.financial_api.repository.TransactionRepository;
import com.xavierBank.financial_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts") public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @PostMapping public ResponseEntity<Account> createAccount(
            @RequestBody
            @Valid AccountRequest request){
        Account account = service.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(
            @PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getStatement(
            @PathVariable UUID id,
            @RequestParam LocalDate data_inicial,
            @RequestParam LocalDate data_final) {

        List<Transaction> list = transactionRepository
                .findByAccount_AccountIdAndDateBetween(id, data_inicial, data_final);

        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(
            @PathVariable UUID id,
            @RequestBody AccountRequest request) {
            return ResponseEntity.ok(service.update(id, request));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id) {service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping ("/{id}/update/transaction")
    public ResponseEntity<Transaction> creatTansaction(
            @PathVariable UUID id,
            @RequestBody @Valid TransactionRequest request) {
        Transaction transaction= service.createTransaction(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<AccountBalance> getBalance(@PathVariable UUID id) {

        Account account = service.getById(id);

        AccountBalance balance = balanceRepository.findById(id)
                .orElse(null);

        if (balance == null) {
            balance = new AccountBalance();
            balance.setAccountId(id);
            balance.setSaldo(BigDecimal.ZERO);
        }

        return ResponseEntity.ok(balance);
    }
}

