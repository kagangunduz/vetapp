package com.kagangunduz.vet.exception;

public class RecordAlreadyExistException extends RuntimeException {
    public RecordAlreadyExistException(String message) {
        super(message);
    }
}
