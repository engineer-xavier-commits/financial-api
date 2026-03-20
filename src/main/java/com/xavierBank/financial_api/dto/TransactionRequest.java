package com.xavierBank.financial_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest {

    private LocalDateTime date;
    private String TransactionType;
    private BigDecimal amount;
    private String description;
}
