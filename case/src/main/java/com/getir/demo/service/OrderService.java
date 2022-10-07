package com.getir.demo.service;

import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.OrderDto;

/**
 * OrderService
 * Author: mcaylak
 * Since : 6.10.2022
 */
public interface OrderService {
    OrderDto placeOrder(PlaceOrderRequest orderRequest);
}
