package com.semester3.davines.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidProductException extends ResponseStatusException {

    public InvalidProductException() { super(HttpStatus.BAD_REQUEST, "PRODUCT_INVALID"); }

    public InvalidProductException(String errorCode) { super(HttpStatus.BAD_REQUEST, errorCode); }
}
