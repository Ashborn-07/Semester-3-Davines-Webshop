package com.semester3.davines.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserIdException extends ResponseStatusException {

    public InvalidUserIdException() {
        super(HttpStatus.BAD_REQUEST, "INVALID_USER_ID");
    }
}
