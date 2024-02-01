package com.myspace.banking.application.controller;

import com.myspace.banking.application.model.*;
import com.myspace.banking.application.service.BankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/banking")
@RequiredArgsConstructor
public class BankingController {

    private final BankingService bankingService;

    @PostMapping("/addCustomer/{accountHolder}")
    public ResponseEntity<AccountHolder> addCustomer(@PathVariable String accountHolder) {
        AccountHolder customer = bankingService.createAccountHolder(accountHolder);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/addAccount/{accountHolderId}")
    public ResponseEntity<Void> addAccount(@PathVariable Long accountHolderId, @RequestBody CreateAccountRequest request) {
        bankingService.createAccountForHolder(accountHolderId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/getCustomer/{accountHolderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetailsResponse> getCustomer(@PathVariable Long accountHolderId) {
        CustomerDetailsResponse accountHolderDetails = bankingService.getAccountHolderDetails(accountHolderId);
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderDetails);
    }

    @PutMapping(value = "/creditDebit/{accountHolderId}")
    public ResponseEntity<Void> creditDebitOperation(@PathVariable Long accountHolderId, @RequestBody CreditDebitAmountRequest request) {
        bankingService.creditDebitMoney(accountHolderId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/transfer")
    public ResponseEntity<Void> transferMoney(@RequestBody MoneyTransferRequest request) {
        bankingService.transferMoney(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
