package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Address
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Entity(name = "addresses")
public class Address extends AbstractBaseEntityId {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "postal_code", nullable = false)
    private String postalCode;
    @Column(name = "full_address", nullable = false)
    private String fullAddress;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "country", nullable = false)
    private String country;
    @OneToOne(mappedBy = "address")
    private Order order;
}
