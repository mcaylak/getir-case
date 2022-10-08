package com.getir.demo.repository;

import com.getir.demo.common.projection.CustomerMonthlyStatisticsResponse;
import com.getir.demo.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * OrderRepository
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);

    Page<Order> findOrderByCreatedDateBetween(Date startDate, Date endDate, Pageable pageable);

    @Query(value = "SELECT to_char(o.created_date,'month') as month,COUNT(o.customer_id) as totalOrderCount,SUM(o.total_price) as totalPrice,SUM(ob.quantity) as totalPurchasedAmount " +
            "FROM orders as o " +
            "INNER JOIN order_book ob " +
            "ON o.id=ob.order_id " +
            "WHERE o.customer_id = :customerId " +
            "group by month " +
            "order by month ASC", nativeQuery = true)
    List<CustomerMonthlyStatisticsResponse> customerMonthlyStatistics(Long customerId);

}
