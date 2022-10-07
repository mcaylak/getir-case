package com.getir.demo.service.impl;

import com.getir.demo.common.exception.CustomerIdNotMatchWithTokenException;
import com.getir.demo.common.mapper.OrderMapper;
import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.AddressDto;
import com.getir.demo.dto.BookDto;
import com.getir.demo.dto.CustomerDto;
import com.getir.demo.dto.OrderDto;
import com.getir.demo.repository.OrderRepository;
import com.getir.demo.security.service.AuthService;
import com.getir.demo.service.AddressService;
import com.getir.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * OrderServiceImpl
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private AddressService addressService;
    private AuthService authService;
    private OrderMapper orderMapper;

    @Override
    public OrderDto placeOrder(PlaceOrderRequest orderRequest) {

        if (!authService.getCurrentUser().getId().equals(orderRequest.getCustomerId())) {
            throw new CustomerIdNotMatchWithTokenException("Customer id and token credentials do not match!");
        }

        AddressDto addressDto = addressService.createAddress(orderRequest);
        OrderDto orderDto = OrderDto.builder()
                .orderDate(LocalDateTime.now())
                .address(addressDto)
                .book(BookDto.builder()
                        .id(orderRequest.getBookId())
                        .build())
                .customer(CustomerDto.builder()
                        .id(orderRequest.getCustomerId())
                        .build())
                .build();

        return orderMapper.mapToDto(orderRepository.save(orderMapper.mapToEntity(orderDto)));

    }

}
