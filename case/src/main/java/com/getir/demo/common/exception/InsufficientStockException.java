package com.getir.demo.common.exception;

/**
 * InsufficientStockException
 * Author: mcaylak
 * Since : 8.10.2022
 */
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(Throwable cause) {
        super(cause);
    }

}
