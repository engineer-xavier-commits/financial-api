package com.xavierBank.financial_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Account {

    @Id
    private UUID accountId;

    private String customerName;
    private String documentNumber;
    private String email;

    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
