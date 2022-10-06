package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Book
 * Author: mcaylak
 * Since : 5.10.2022
 */

@Getter
@Setter
@Entity(name = "books")
public class Book extends AbstractBaseEntityId {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "description")
    private String description;

}
