package com.getir.demo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Customer
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Builder
public class CustomerDto {

    private String name;
    private String username;
    private String password;
    private String email;

}
