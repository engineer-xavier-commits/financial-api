package com.xavierBank.financial_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
public class Account {


    @Id
    @GeneratedValue
    public void setAccountId(UUID accountId) {
    }
    public void setCustomerName(String customerName) {
    }
    public void setDocumentNumber(String documentNumber) {
    }
    public void setEmail(String email) {
    }
    public void setActive(boolean active) {
    }
    public void setCreatedAt(LocalDateTime createdAt) {
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
    }



}
