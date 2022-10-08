package com.getir.demo.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * OrderEntryRequest
 * Author: mcaylak
 * Since : 8.10.2022
 */

@Getter
@Setter
public class OrderEntryRequest {

    @NotNull(message = "Book id must not be null")
    @Max(value = Long.MAX_VALUE, message = "Book id cannot exceed the max value")
    private Long bookId;

    @NotNull(message = "Quantity must not be null")
    @Max(value = Long.MAX_VALUE, message = "Quantity cannot exceed the max value")
    private Long quantity;

    @NotNull(message = "Entry price must not be null")
    @Max(value = Long.MAX_VALUE, message = "Entry price cannot exceed the max value")
    private Long entryPrice;

}
