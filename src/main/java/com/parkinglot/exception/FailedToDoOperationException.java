package com.parkinglot.exception;

public class FailedToDoOperationException extends RuntimeException {

    public FailedToDoOperationException() {
        super("Failed to do the operation.");
    }
}
