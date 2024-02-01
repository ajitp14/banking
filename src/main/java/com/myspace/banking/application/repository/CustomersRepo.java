package com.myspace.banking.application.repository;

import com.myspace.banking.application.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepo extends JpaRepository<AccountHolder, Long> {
}
