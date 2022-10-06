package com.getir.demo.repository;

import com.getir.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AddressRepository
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
