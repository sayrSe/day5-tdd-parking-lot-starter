package com.parkinglot.exception;

public class NoPositionException extends RuntimeException {
    public NoPositionException() {
        super("No available position.");
    }
}
