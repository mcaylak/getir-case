package com.getir.demo.repository;

import com.getir.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
