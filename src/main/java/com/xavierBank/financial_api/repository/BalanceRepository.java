package com.xavierBank.financial_api.repository;

import com.xavierBank.financial_api.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceRepository extends JpaRepository<AccountBalance, UUID> {
}