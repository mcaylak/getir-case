package com.getir.demo.common.exception;

/**
 * IdNotFoundException
 * Author: mcaylak
 * Since : 8.10.2022
 */
public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super();
    }

    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException(Throwable cause) {
        super(cause);
    }

}
