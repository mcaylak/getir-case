package com.getir.demo.common.exception;

/**
 * CustomerIdNotMatchWithTokenException
 * Author: mcaylak
 * Since : 7.10.2022
 */
public class CustomerIdNotMatchWithTokenException extends RuntimeException {

    public CustomerIdNotMatchWithTokenException() {
        super();
    }

    public CustomerIdNotMatchWithTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerIdNotMatchWithTokenException(String message) {
        super(message);
    }

    public CustomerIdNotMatchWithTokenException(Throwable cause) {
        super(cause);
    }

}
