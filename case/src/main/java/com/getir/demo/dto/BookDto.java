package com.getir.demo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Book
 * Author: mcaylak
 * Since : 6.10.2022
 */
@Getter
@Setter
@Builder
public class BookDto {

    private String name;
    private Long price;
    private Long stock;
    private String description;

}
