package com.myspace.banking.application.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Error {

    private String code;
    private String message;
    private String details;
}
