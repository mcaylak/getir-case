package com.getir.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OrderEntryDto
 * Author: mcaylak
 * Since : 8.10.2022
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntryDto {

    private OrderDto order;
    private BookDto book;
    private Long quantity;
    private Long entryPrice;

}
