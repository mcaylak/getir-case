package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * OrderItem
 * Author: mcaylak
 * Since : 8.10.2022
 */

@Getter
@Setter
@Entity(name = "order_book")
public class OrderEntry extends AbstractBaseEntityId {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "entry_price", nullable = false)
    private Long entryPrice;

}
