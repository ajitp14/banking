package com.myspace.banking.application.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MoneyTransferRequest {

    private String accountIdOfSender;
    private String accountNumberOfSender;
    private String balanceToTransfer;
    private String accountIdOfReceiver;
    private String accountNumberOfReceiver;

}
