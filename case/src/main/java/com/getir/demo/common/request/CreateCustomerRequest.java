package com.getir.demo.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * CreateCustomerRequest
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
public class CreateCustomerRequest {

    @NotBlank(message = "name is must not be null")
    private String name;

    @NotBlank(message = "username is must not be null")
    private String username;

    @NotBlank(message = "password is must not be null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password should be minimum eight characters, at least one letter and one number")
    private String password;

    @NotBlank(message = "email is must not be null")
    private String email;

}
