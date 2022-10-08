package com.getir.demo.service.impl;

import com.getir.demo.common.exception.CustomerIdNotMatchWithTokenException;
import com.getir.demo.common.exception.IdNotFoundException;
import com.getir.demo.common.exception.InsufficientStockException;
import com.getir.demo.common.mapper.OrderEntryMapper;
import com.getir.demo.common.mapper.OrderMapper;
import com.getir.demo.common.projection.CustomerMonthlyStatisticsResponse;
import com.getir.demo.common.request.OrderEntryRequest;
import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.AddressDto;
import com.getir.demo.dto.BookDto;
import com.getir.demo.dto.CustomerDto;
import com.getir.demo.dto.OrderDto;
import com.getir.demo.dto.OrderEntryDto;
import com.getir.demo.entity.Order;
import com.getir.demo.entity.OrderEntry;
import com.getir.demo.repository.OrderEntryRepository;
import com.getir.demo.repository.OrderRepository;
import com.getir.demo.security.service.AuthService;
import com.getir.demo.service.AddressService;
import com.getir.demo.service.BookService;
import com.getir.demo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * OrderServiceImpl
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderEntryRepository orderEntryRepository;
    private BookService bookService;
    private AddressService addressService;
    private AuthService authService;
    private OrderMapper orderMapper;
    private OrderEntryMapper orderEntryMapper;

    @Override
    @Transactional
    public OrderDto placeOrder(PlaceOrderRequest orderRequest) {

        placeOrderValidations(orderRequest);

        AddressDto addressDto = addressService.createAddress(orderRequest);

        OrderDto orderDto = OrderDto.builder()
                .orderDate(LocalDateTime.now())
                .address(addressDto)
                .customer(CustomerDto.builder()
                        .id(orderRequest.getCustomerId())
                        .build())
                .totalPrice(orderRequest.getTotalPrice())
                .build();

        Order savedOrder = orderRepository.save(orderMapper.mapToEntity(orderDto));

        List<OrderEntry> orderEntryList = orderEntryRepository.saveAll(orderEntryMapper
                .map2EntityList(getEntryDtoList(orderRequest.getOrderEntryRequests(),
                        orderMapper.mapToDto(savedOrder))));

        updateBookStock(orderEntryList);
        savedOrder.setOrderEntries(orderEntryList);

        return orderMapper.mapToDto(savedOrder);
    }


    @Override
    public Page<OrderDto> findAllByCustomerId(Long customerId, int page, int size) {
        return orderRepository.findAllByCustomerId(customerId, PageRequest.of(page, size))
                .map(order -> orderMapper.mapToDto(order));
    }

    @Override
    public OrderDto getOrderDetailsById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return orderMapper.mapToDto(order.get());
        }

        throw new IdNotFoundException("Order Id {" + orderId + "} not found in database!");
    }

    @Override
    public Page<OrderDto> findOrderByCreatedDateBetween(Date startDate, Date endDate, int page, int size) {
        return orderRepository.findOrderByCreatedDateBetween(startDate, endDate, PageRequest.of(page, size))
                .map(order -> orderMapper.mapToDto(order));
    }

    @Override
    public List<CustomerMonthlyStatisticsResponse> customerMonthlyStatistics(Long customerId) {
        return orderRepository.customerMonthlyStatistics(customerId);
    }

    /**
     * Place order request in entries converted to entry dto list
     *
     * @param orderEntryRequests List<OrderEntryRequest>
     * @return List<OrderEntryDto>
     */
    private List<OrderEntryDto> getEntryDtoList(List<OrderEntryRequest> orderEntryRequests, OrderDto order) {
        List<OrderEntryDto> orderEntryDtoList = new ArrayList<>();
        orderEntryRequests.forEach(entryReq -> orderEntryDtoList.add(OrderEntryDto.builder()
                .book(BookDto.builder()
                        .id(entryReq.getBookId()).build())
                .entryPrice(entryReq.getEntryPrice())
                .quantity(entryReq.getQuantity())
                .order(order)
                .build()));
        return orderEntryDtoList;
    }

    /**
     * Validations, token / id control and stock control that should be done before ordering
     *
     * @param orderRequest PlaceOrderRequest
     */
    private void placeOrderValidations(PlaceOrderRequest orderRequest) {

        if (!authService.getCurrentUser().getId().equals(orderRequest.getCustomerId())) {
            log.error("Customer id {} and token credentials in id {} do not match!", authService.getCurrentUser().getId(),
                    orderRequest.getCustomerId());
            throw new CustomerIdNotMatchWithTokenException("Customer id and token credentials do not match!");
        }

        orderRequest.getOrderEntryRequests().forEach(orderEntry -> {
            if (!(bookService.getBookStockByBookId(orderEntry.getBookId()) >= orderEntry.getQuantity())) {
                log.error("Insufficient Stock! for this book {} qty {}", orderEntry.getBookId(), orderEntry.getQuantity());
                throw new InsufficientStockException("Insufficient Stock!");
            }
        });
    }

    /**
     * After the order was placed, the book stocks were updated.
     *
     * @param orderEntryList List<OrderEntry>
     */
    private void updateBookStock(List<OrderEntry> orderEntryList) {
        orderEntryList.forEach(orderEntry -> {
            BookDto bookDto =
                    bookService.reduceStock(orderEntry.getBook().getId(), orderEntry.getQuantity());
            log.info("Book {} stock successfully updated!", bookDto.getId());
        });
    }
}
