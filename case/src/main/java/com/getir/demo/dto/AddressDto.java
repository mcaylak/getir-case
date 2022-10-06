package com.getir.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * AddressDto
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Builder
public class AddressDto {

    private String name;
    private String surname;
    private String postalCode;
    private String fullAddress;
    private String city;
    private String country;

}
