package com.taoist.demo.exceptions;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private final List<String> errors;

    public BadRequestException(List<String> errors) {
        super("Bad Request");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}