package com.xavierBank.financial_api.dto;

public class AccountRequest {
    private String customerName;
    private String documentNumber;
    private String email;

    public String getCustomerName() {
        return customerName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }
}

