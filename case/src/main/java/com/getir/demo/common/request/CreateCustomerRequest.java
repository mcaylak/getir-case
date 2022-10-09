package com.getir.demo.common.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * CreateCustomerRequest
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Builder
public class CreateCustomerRequest {

    @Size(min = 3, message = "Name must be at least 3 characters")
    @Size(max = 20, message = "Name must be no more than 20 characters")
    @NotBlank(message = "name is must not be null")
    private String name;

    @Size(min = 3, message = "Username must be at least 3 characters")
    @Size(max = 20, message = "Username must be no more than 20 characters")
    @NotBlank(message = "username is must not be null")
    private String username;

    @NotBlank(message = "password is must not be null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password should be minimum eight characters, at least one letter and one number")
    private String password;

    @NotBlank(message = "Email is must not be null")
    private String email;

}
