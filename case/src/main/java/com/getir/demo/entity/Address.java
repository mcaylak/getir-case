package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Address
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Entity(name = "addresses")
public class Address extends AbstractBaseEntityId {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "full_address")
    private String fullAddress;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
}
