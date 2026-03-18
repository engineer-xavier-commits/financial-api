package com.xavierBank.financial_api.repository;

import com.xavierBank.financial_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
