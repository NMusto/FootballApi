package com.football.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InfoExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public InfoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
