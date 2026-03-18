package com.xavierBank.financial_api.repository;

import com.xavierBank.financial_api.model.Account;
import com.xavierBank.financial_api.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface balanceRepository extends JpaRepository<AccountBalance, UUID> {
}