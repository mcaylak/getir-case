package com.getir.demo.common.exception;

import com.getir.demo.common.constants.ErrorCode;
import com.getir.demo.common.response.ResponseBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler
 * Author: mcaylak
 * Since : 6.10.2022
 */

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseBean<Object>> exceptionHandler(Exception e) {
        ResponseBean<Object> responseBean = new ResponseBean<>(ErrorCode.SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @ExceptionHandler(value = {CustomerIdNotMatchWithTokenException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseBean<Object> entityAlreadyExistHandler(CustomerIdNotMatchWithTokenException e) {
        return new ResponseBean<>(ErrorCode.CUSTOMER_ID_NOT_MATCH_WITH_TOKEN, e.getMessage());
    }

}
