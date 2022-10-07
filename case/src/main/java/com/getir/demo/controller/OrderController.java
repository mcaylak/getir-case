package com.getir.demo.controller;

import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.OrderDto;
import com.getir.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * OrderController
 * Author: mcaylak
 * Since : 7.10.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/order/")
public class OrderController {

    private OrderService orderService;

    @PostMapping("create")
    public ResponseBean<OrderDto> placeOrder(@Valid @RequestBody PlaceOrderRequest orderRequest) {
        return new ResponseBean<>(orderService.placeOrder(orderRequest));
    }

}
