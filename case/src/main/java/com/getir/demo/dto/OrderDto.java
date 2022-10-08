package com.getir.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderDto
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private CustomerDto customer;
    private List<OrderEntryDto> orderEntryDtoList;
    private Long totalPrice;
    private LocalDateTime orderDate;
    private AddressDto address;

}
