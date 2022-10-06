package com.getir.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * AbstractBaseEntityId
 * Author: mcaylak
 * Since : 5.10.2022
 */

@Getter
@Setter
@MappedSuperclass
public class AbstractBaseEntityId extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_entity_id")
    @SequenceGenerator(name = "base_entity_id", sequenceName = "base_entity_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
}
