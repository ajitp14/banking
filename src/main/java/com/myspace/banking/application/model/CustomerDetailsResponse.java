package com.myspace.banking.application.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CustomerDetailsResponse {

    private Long id;
    private String customerName;

    private List<AccountDetailsResponse> customerAccounts;

}
