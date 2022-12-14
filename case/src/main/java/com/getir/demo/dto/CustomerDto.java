package com.getir.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;

}
