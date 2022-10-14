package com.semester3.davines.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyUsedException extends ResponseStatusException {
    public EmailAlreadyUsedException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_USED");
    }
}

