package com.myspace.banking.application.model;

import lombok.Data;

@Data
public class EnumClass {

    public enum AccountType {
        SAVINGS("Savings Account"),
        CURRENT("Current Account"),
        LOAN("Loan Account");
        private final String description;
        AccountType(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
    }
}
