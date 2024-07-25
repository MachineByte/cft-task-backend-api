package ru.cft.task_backend.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadStateException extends RuntimeException{
    public BadStateException(String message) {
        super(message);
    }
}