package com.xavierBank.financial_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AccountBalance {

    @Id
    private UUID accountId;

    private BigDecimal saldo;
    private LocalDateTime updatedAt;

}
