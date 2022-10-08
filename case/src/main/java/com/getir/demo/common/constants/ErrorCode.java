package com.getir.demo.common.constants;

import lombok.Getter;

/**
 * ErrorCode
 * Author: mcaylak
 * Since : 6.10.2022
 */
@Getter
public class ErrorCode {
    public static final Integer OK = 200;
    public static final Integer SERVER_ERROR = 500;
    public static final Integer CUSTOMER_ID_NOT_MATCH_WITH_TOKEN = 9000000;
    public static final Integer INSUFFICIENT_STOCK_EXCEPTION = 9000001;
}
