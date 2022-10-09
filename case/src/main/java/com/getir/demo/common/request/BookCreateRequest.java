package com.getir.demo.common.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * BookCreateRequest
 * Author: mcaylak
 * Since : 7.10.2022
 */

@Getter
@Setter
@Builder
public class BookCreateRequest {

    @Size(min = 3, message = "Name must be at least 3 characters")
    @Size(max = 50, message = "Name must be no more than 50 characters")
    @NotBlank(message = "Name is must not be null")
    private String name;

    @NotNull(message = "Price must not be null")
    @Max(value = Long.MAX_VALUE, message = "Total price cannot exceed the max value")
    private Long price;

    @NotNull(message = "Total price must not be null")
    @Max(value = Long.MAX_VALUE, message = "Total price cannot exceed the max value")
    private Long stock;

    @Size(min = 3, message = "Description must be at least 3 characters")
    @Size(max = 250, message = "Description must be no more than 250 characters")
    @NotBlank(message = "Description is must not be null")
    private String description;

}
