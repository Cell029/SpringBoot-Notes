package com.cell.notes.web.exception;

public class TransferException extends RuntimeException {
    public TransferException() {
    }
    public TransferException(String message) {
        super(message);
    }
}
