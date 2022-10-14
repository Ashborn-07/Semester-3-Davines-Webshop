package com.semester3.davines.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidSeriesException extends ResponseStatusException {

    public InvalidSeriesException() { super(HttpStatus.BAD_REQUEST, "SERIES_INVALID"); }

    public InvalidSeriesException(String errorCode) { super(HttpStatus.BAD_REQUEST, errorCode); }
}
