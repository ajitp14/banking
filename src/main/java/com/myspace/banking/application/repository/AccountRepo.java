package com.myspace.banking.application.repository;

import com.myspace.banking.application.model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<AccountDetails, Long> {
}
