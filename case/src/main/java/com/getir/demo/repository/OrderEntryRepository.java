package com.getir.demo.repository;

import com.getir.demo.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderItemRepository
 * Author: mcaylak
 * Since : 8.10.2022
 */
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
