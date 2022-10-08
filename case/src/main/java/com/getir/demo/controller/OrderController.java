package com.getir.demo.controller;

import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.OrderDto;
import com.getir.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("findAllByCustomerId")
    public ResponseBean<Page<OrderDto>> findAllByCustomerId(@RequestParam(required = true) Long customerId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        return new ResponseBean<>(orderService.findAllByCustomerId(customerId, page, size));
    }

    @GetMapping("orderDetails")
    public ResponseBean<OrderDto> getOrderDetails(@RequestParam(required = true) Long orderId) {
        return new ResponseBean<>(orderService.getOrderDetailsById(orderId));
    }


}
