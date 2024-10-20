package com.taoist.demo.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    private final List<String> errors;

    public BadRequestException(List<String> errors) {
        super("Bad Request");
        this.errors = errors;
    }

}