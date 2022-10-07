package com.getir.demo.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * PlaceOrderRequest
 * Author: mcaylak
 * Since : 7.10.2022
 */

@Getter
@Setter
public class PlaceOrderRequest {

    @NotNull(message = "Customer Id must not be null")
    private Long customerId;

    @NotNull(message = "Book Id must not be null")
    private Long bookId;

    @NotNull(message = "Total price must not be null")
    @Max(value = Long.MAX_VALUE, message = "Total price cannot exceed the max value")
    private Long totalPrice;

    @NotBlank(message = "Name is must not be null")
    @Size(min = 3, message = "Name must be at least 3 characters")
    @Size(max = 20, message = "Name must be no more than 20 characters")
    private String name;

    @Size(min = 3, message = "Surname must be at least 3 characters")
    @Size(max = 20, message = "Surname must be no more than 20 characters")
    @NotBlank(message = "Surname is must not be null")
    private String surname;

    @NotBlank(message = "PostalCode is must not be null")
    private String postalCode;

    @Size(min = 10, message = "Address must be at least 10 characters")
    @Size(max = 50, message = "Address must be no more than 50 characters")
    @NotBlank(message = "Address is must not be null")
    private String fullAddress;

    @Size(min = 3, message = "City must be at least 3 characters")
    @Size(max = 20, message = "City must be no more than 20 characters")
    @NotBlank(message = "City is must not be null")
    private String city;

    @Size(min = 3, message = "Country must be at least 3 characters")
    @Size(max = 20, message = "Country must be no more than 20 characters")
    @NotBlank(message = "Country is must not be null")
    private String country;

}
