package com.myspace.banking.application.service;

import com.myspace.banking.application.exception.NoFundsException;
import com.myspace.banking.application.model.*;
import com.myspace.banking.application.repository.CustomersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankingService {


    private final CustomersRepo accountHolderRepository;


    private final AccountService accountDetailsService;

    @Transactional
    public AccountHolder createAccountHolder(String name) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(name);
        return accountHolderRepository.save(accountHolder);
    }

    @Transactional
    public void createAccountForHolder(Long accountHolderId, CreateAccountRequest createAccountRequest) {
        AccountHolder accountHolder = accountHolderRepository.findById(accountHolderId)
                .orElseThrow(() -> new RuntimeException("Account Holder not found"));
        AccountDetails accountDetails = new AccountDetails();
        String accountNumber = Utils.generateCustomerNumber();
        accountDetails.setAccountNumber(accountNumber);
        accountDetails.setOpenedOn(Date.valueOf(LocalDate.now()));
        accountDetails.setAccountType(accountDetails.getAccountType());
        accountDetails.setBalance(Double.parseDouble(createAccountRequest.getBalance()));
        accountDetails.setAccountHolder(accountHolder);

        accountDetailsService.createAccount(accountDetails);
    }


    public CustomerDetailsResponse getAccountHolderDetails(Long accountHolderId) {
        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        AccountHolder accountHolder = accountHolderRepository.findById(accountHolderId).orElseThrow();
        customerDetailsResponse.setId(accountHolder.getId());
        customerDetailsResponse.setCustomerName(accountHolder.getName());
        List<AccountDetails> accounts = accountHolder.getAccounts();
        List<AccountDetailsResponse> collect = accounts.stream().map(accountDetails -> {
            AccountDetailsResponse accountDetail = new AccountDetailsResponse();
            accountDetail.setAccountNumber(accountDetails.getAccountNumber());
            accountDetail.setBalance(accountDetails.getBalance());
            return accountDetail;
        }).collect(Collectors.toList());
        customerDetailsResponse.setCustomerAccounts(collect);

        return customerDetailsResponse;
    }

    @Transactional
    public void creditDebitMoney(Long accountHolderId, CreditDebitAmountRequest creditDebitAmountRequest) {
        AccountHolder accountHolder = accountHolderRepository.findById(accountHolderId).orElseThrow();
        List<AccountDetails> accounts = accountHolder.getAccounts();
        List<AccountDetails> accountDetailsForTransaction = accounts.stream().filter(accountDetails -> accountDetails.getAccountNumber().equalsIgnoreCase(creditDebitAmountRequest.getAccountNumber())).collect(Collectors.toList());
        AccountDetails accountDetails = accountDetailsForTransaction.stream().findFirst().orElseThrow();
        if(creditDebitAmountRequest.getOperation().equalsIgnoreCase("CREDIT")) {
            accountDetails.setBalance(accountDetails.getBalance() + Double.parseDouble(creditDebitAmountRequest.getBalance()));
        } else if(creditDebitAmountRequest.getOperation().equalsIgnoreCase("DEBIT")){
            double moneyToDebit = accountDetails.getBalance() - Double.parseDouble(creditDebitAmountRequest.getBalance());
            if(moneyToDebit < 0){
                throw new NoFundsException("1001","Not Enough Funds","Sorry, you don't have sufficient funds to withdraw "+creditDebitAmountRequest.getBalance());
            }
            accountDetails.setBalance(accountDetails.getBalance() - Double.parseDouble(creditDebitAmountRequest.getBalance()));
        }
        accountHolderRepository.save(accountHolder);
    }

    @Transactional
    public void transferMoney(MoneyTransferRequest moneyTransferRequest) {
        AccountHolder accountHolderOfSender = accountHolderRepository.findById(Long.parseLong(moneyTransferRequest.getAccountIdOfSender()))
                .orElseThrow();

        AccountHolder accountHolderOfReceiver = accountHolderRepository.findById(Long.parseLong(moneyTransferRequest.getAccountIdOfReceiver()))
                .orElseThrow();

        List<AccountDetails> accountsOfSender = accountHolderOfSender.getAccounts();
        List<AccountDetails> accountFromWhichMoneyTransfer = accountsOfSender.stream().filter
                (accountDetails -> accountDetails.getAccountNumber().equalsIgnoreCase(moneyTransferRequest.getAccountNumberOfSender())).collect(Collectors.toList());
        AccountDetails accountDetailsFromWhichMoneyTransfer = accountFromWhichMoneyTransfer.stream().findFirst().orElseThrow();
        double moneyToTransfer = accountDetailsFromWhichMoneyTransfer.getBalance() - Double.parseDouble(moneyTransferRequest.getBalanceToTransfer());
        if(moneyToTransfer < 0){
            throw new NoFundsException("1001","Not Enough Funds","Sorry, you don't have sufficient funds to transfer "+moneyToTransfer);
        }
        List<AccountDetails> accountsOfReceiver = accountHolderOfReceiver.getAccounts();
        List<AccountDetails> accountToWhichMoneyTransferred = accountsOfReceiver.stream().filter
                (accountDetails -> accountDetails.getAccountNumber().equalsIgnoreCase(moneyTransferRequest.getAccountNumberOfReceiver())).collect(Collectors.toList());
        AccountDetails accountDetailsToWhichMoneyTransferred = accountToWhichMoneyTransferred.stream().findFirst().orElseThrow();

        accountDetailsFromWhichMoneyTransfer.setBalance(moneyToTransfer);
        accountDetailsToWhichMoneyTransferred.setBalance(accountDetailsToWhichMoneyTransferred.getBalance() + Double.parseDouble(moneyTransferRequest.getBalanceToTransfer()));

        accountHolderRepository.save(accountHolderOfSender);
        accountHolderRepository.save(accountHolderOfReceiver);
    }
}