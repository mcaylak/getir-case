package com.getir.demo.repository;

import com.getir.demo.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderRepository
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);

}
