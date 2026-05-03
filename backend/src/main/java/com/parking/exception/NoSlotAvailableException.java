package com.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSlotAvailableException extends RuntimeException {
    public NoSlotAvailableException(String message) {
        super(message);
    }
}
