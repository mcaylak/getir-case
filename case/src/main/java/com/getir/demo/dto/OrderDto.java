package com.getir.demo.dto;

import com.getir.demo.entity.Address;
import com.getir.demo.entity.Book;
import com.getir.demo.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private Customer customer;
    private Book book;
    private Long totalPrice;
    private LocalDateTime orderDate;
    private Address address;

}
