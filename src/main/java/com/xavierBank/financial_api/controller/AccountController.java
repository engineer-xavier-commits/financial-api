package com.xavierBank.financial_api.controller;

import com.xavierBank.financial_api.dto.AccountRequest;
import com.xavierBank.financial_api.model.Account;
import com.xavierBank.financial_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseEntity<Account> createAccount(
            @RequestBody AccountRequest request){

        Account account = service.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(account);


    }

}
