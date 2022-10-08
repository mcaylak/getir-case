package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order
 * Author: mcaylak
 * Since : 5.10.2022
 */

@Getter
@Setter
@Entity(name = "orders")
public class Order extends AbstractBaseEntityId {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderEntry> orderEntries;

    @Column(name = "totalPrice")
    private Long totalPrice;

    @Column(name = "orderDate", nullable = false)
    private LocalDateTime orderDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
