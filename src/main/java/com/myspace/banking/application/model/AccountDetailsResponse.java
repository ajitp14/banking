package com.myspace.banking.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AccountDetailsResponse {

    private String accountNumber;
    private double balance;

}
