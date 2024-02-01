package com.myspace.banking.application.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CreateAccountRequest {

    private String accountType;
    private String balance;

}
