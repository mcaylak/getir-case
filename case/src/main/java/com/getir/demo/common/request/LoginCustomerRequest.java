package com.getir.demo.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * LoginCustomerRequest
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
public class LoginCustomerRequest {

    @NotBlank(message = "username is must not be null")
    private String username;

    @NotBlank(message = "password is must not be null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password should be minimum eight characters, at least one letter and one number")
    private String password;

}
