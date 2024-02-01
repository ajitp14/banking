package com.myspace.banking.application.service;

import com.myspace.banking.application.model.AccountDetails;
import com.myspace.banking.application.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepo accountDetailsRepository;

    @Transactional
    public AccountDetails createAccount(AccountDetails accountDetails) {
        return accountDetailsRepository.save(accountDetails);
    }
}
