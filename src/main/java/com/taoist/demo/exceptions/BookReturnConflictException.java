package com.taoist.demo.exceptions;

public class BookReturnConflictException extends RuntimeException {
    public BookReturnConflictException(String message) {
        super(message);
    }
}