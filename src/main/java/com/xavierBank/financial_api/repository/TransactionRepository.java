package com.xavierBank.financial_api.repository;

import com.xavierBank.financial_api.entity.Transaction;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount_AccountIdAndDateBetween(
    UUID accountId,
    LocalDate start,
    LocalDate end
    );

}
