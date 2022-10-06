package com.getir.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * AbstractBaseEntity
 * Author: mcaylak
 * Since : 5.10.2022
 */

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AbstractBaseEntity {

    @Column(updatable = false)
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

}
