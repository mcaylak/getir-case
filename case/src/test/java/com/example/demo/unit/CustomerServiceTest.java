package com.example.demo.unit;

import com.getir.demo.common.mapper.CustomerMapper;
import com.getir.demo.common.request.CreateCustomerRequest;
import com.getir.demo.dto.CustomerDto;
import com.getir.demo.entity.Customer;
import com.getir.demo.repository.CustomerRepository;
import com.getir.demo.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * CustomerServiceTest
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("Test customer service");
        customer.setEmail("test@test.com");

        customerDto = CustomerDto.builder()
                .name("Test customer service")
                .email("test@test.com")
                .build();
    }

    @Test
    void createCustomer_successfullyCreatedCustomer_Test() {

        CreateCustomerRequest createCustomerRequest = prepareCreateCustomerRequest();

        given(customerRepository.save(any(Customer.class))).willReturn(customer);
        given(bCryptPasswordEncoder.encode(any(String.class))).willReturn("testpassword00");
        given(customerMapper.mapToDto(any(Customer.class))).willReturn(customerDto);
        given(customerMapper.mapToEntity(any(CustomerDto.class))).willReturn(customer);


        CustomerDto createdCustomer = customerService.createCustomer(createCustomerRequest);
        assertEquals(customerDto.getEmail(), createdCustomer.getEmail());

    }

    private CreateCustomerRequest prepareCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .name("Test customer service")
                .email("test@test.com")
                .username("test")
                .password("testpassword00")
                .build();
    }
}
