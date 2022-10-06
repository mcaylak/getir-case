package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * Order
 * Author: mcaylak
 * Since : 5.10.2022
 */

@Getter
@Setter
@Entity(name = "orders")
public class Order extends AbstractBaseEntityId {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "totalPrice")
    private Long totalPrice;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
