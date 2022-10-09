package com.example.demo.unit;

import com.getir.demo.common.exception.CustomerIdNotMatchWithTokenException;
import com.getir.demo.common.exception.InsufficientStockException;
import com.getir.demo.common.mapper.OrderEntryMapper;
import com.getir.demo.common.mapper.OrderMapper;
import com.getir.demo.common.projection.CustomerMonthlyStatisticsResponse;
import com.getir.demo.common.request.OrderEntryRequest;
import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.OrderDto;
import com.getir.demo.entity.Customer;
import com.getir.demo.entity.Order;
import com.getir.demo.repository.OrderEntryRepository;
import com.getir.demo.repository.OrderRepository;
import com.getir.demo.security.service.AuthService;
import com.getir.demo.service.BookService;
import com.getir.demo.service.impl.AddressServiceImpl;
import com.getir.demo.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * OrderServiceTest
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderEntryRepository orderEntryRepository;
    @Mock
    private BookService bookService;
    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private AuthService authService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderEntryMapper orderEntryMapper;

    @Test
    void placeOrder_throwCustomerIdNotMatchWithTokenException_Test() {
        PlaceOrderRequest orderRequest = createExampleCreateOrderRequest();
        Customer customer = new Customer();
        customer.setId(2L);
        given(authService.getCurrentUser()).willReturn(customer);

        CustomerIdNotMatchWithTokenException throwingException = assertThrows(CustomerIdNotMatchWithTokenException.class,
                () -> orderService.placeOrder(orderRequest), "Throwing exception");

        assertEquals("Customer id and token credentials do not match!", throwingException.getMessage());
    }

    @Test
    void placeOrder_throwInsufficientStockException_Test() {

        PlaceOrderRequest orderRequest = createExampleCreateOrderRequest();
        Customer customer = new Customer();
        customer.setId(1L);
        given(authService.getCurrentUser()).willReturn(customer);
        given(bookService.getBookStockByBookId(any(Long.class))).willReturn(0L);

        InsufficientStockException throwingException = assertThrows(InsufficientStockException.class,
                () -> orderService.placeOrder(orderRequest), "Throwing exception");

        assertEquals("Insufficient Stock!", throwingException.getMessage());
    }

    @Test
    void findAllByCustomerId_ReturnSuccessOrders_Test() {

        given(orderRepository.findAllByCustomerId(any(Long.class), any(Pageable.class))).willReturn(Page.empty());
        Page<OrderDto> orderDtoList = orderService.findAllByCustomerId(0L, 0, 3);
        assertEquals(0, orderDtoList.getTotalElements());

    }

    @Test
    void getOrderDetailsById_ReturnSuccessOrderDetail_Test() {

        Order order = new Order();
        order.setId(1L);

        given(orderRepository.findById(any(Long.class))).willReturn(Optional.of(order));
        given(orderMapper.mapToDto(any(Order.class))).willReturn(OrderDto.builder().id(1L).build());
        OrderDto orderDto = orderService.getOrderDetailsById(1L);

        assertEquals(order.getId(), orderDto.getId());

    }

    @Test
    void findOrderByCreatedDateBetween_returnSuccessResult_Test() throws ParseException {
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-01");
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-01");
        given(orderRepository.findOrderByCreatedDateBetween(any(Date.class), any(Date.class), any(Pageable.class))).willReturn(Page.empty());
        Page<OrderDto> orderDtoList = orderService.findOrderByCreatedDateBetween(start, end, 0, 3);
        assertEquals(0, orderDtoList.getTotalElements());
    }

    @Test
    void customerMonthlyStatistics_returnSuccessResult_Test() {

        CustomerMonthlyStatisticsResponse customerMonthlyStatisticsResponse = getExampleResponse();
        given(orderRepository.customerMonthlyStatistics(any(Long.class))).willReturn(List.of(customerMonthlyStatisticsResponse));
        List<CustomerMonthlyStatisticsResponse> statisticsResponses = orderService.customerMonthlyStatistics(1L);

        assertEquals(1, statisticsResponses.size());
        assertEquals("August", statisticsResponses.get(0).getMonth());
        assertEquals(5000L, statisticsResponses.get(0).getTotalPrice());
        assertEquals(1000L, statisticsResponses.get(0).getTotalPurchasedAmount());

    }

    private PlaceOrderRequest createExampleCreateOrderRequest() {
        OrderEntryRequest orderEntryRequest = OrderEntryRequest.builder()
                .bookId(1L)
                .entryPrice(100L)
                .quantity(5L)
                .build();

        return PlaceOrderRequest.builder()
                .name("Test Name")
                .city("Test City")
                .surname("Test Surname")
                .country("Test Country")
                .totalPrice(500L)
                .postalCode("66700")
                .customerId(1L)
                .orderEntryRequests(List.of(orderEntryRequest))
                .build();
    }

    private CustomerMonthlyStatisticsResponse getExampleResponse() {
        return new CustomerMonthlyStatisticsResponse() {
            @Override
            public String getMonth() {
                return "August";
            }

            @Override
            public Long getTotalOrderCount() {
                return 5L;
            }

            @Override
            public Long getTotalPrice() {
                return 5000L;
            }

            @Override
            public Long getTotalPurchasedAmount() {
                return 1000L;
            }
        };
    }
}
