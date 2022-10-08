package com.getir.demo.service;

import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.common.response.CustomerMonthlyStatisticsResponse;
import com.getir.demo.dto.OrderDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * OrderService
 * Author: mcaylak
 * Since : 6.10.2022
 */
public interface OrderService {
    OrderDto placeOrder(PlaceOrderRequest orderRequest);

    Page<OrderDto> findAllByCustomerId(Long customerId, int page, int size);

    OrderDto getOrderDetailsById(Long orderId);

    Page<OrderDto> findOrderByCreatedDateBetween(Date startDate, Date endDate, int page, int size);

    List<CustomerMonthlyStatisticsResponse> customerMonthlyStatistics(Long customerId);
}
