package com.myspace.banking.application.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CreditDebitAmountRequest {

    private String accountNumber;
    private String operation;
    private String balance;
}
