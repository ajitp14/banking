package com.myspace.banking.application.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ErrorResponse {

    private List<Error> error;
}
