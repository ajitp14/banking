package com.myspace.banking.application.exception;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class NoFundsException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
    private String errorDetails;

    public NoFundsException(String s, String not_enough_funds, String s1) {
        this.errorCode = s;
        this.errorMessage = not_enough_funds;
        this.errorDetails = s1;
    }
}
