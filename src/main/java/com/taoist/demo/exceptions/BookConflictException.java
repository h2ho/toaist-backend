package com.taoist.demo.exceptions;

public class BookConflictException extends RuntimeException {
    public BookConflictException(String message) {
        super(message);
    }
}