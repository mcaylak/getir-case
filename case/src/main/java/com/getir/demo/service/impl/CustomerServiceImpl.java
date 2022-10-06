package com.getir.demo.service.impl;

import com.getir.demo.common.mapper.CustomerMapper;
import com.getir.demo.common.request.CreateCustomerRequest;
import com.getir.demo.dto.CustomerDto;
import com.getir.demo.entity.Customer;
import com.getir.demo.repository.CustomerRepository;
import com.getir.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * CustomerServiceImpl
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerRepository.save(customerMapper
                .mapToEntity(CustomerDto.builder()
                        .email(createCustomerRequest.getEmail())
                        .password(bCryptPasswordEncoder.encode(createCustomerRequest.getPassword()))
                        .username(createCustomerRequest.getUsername())
                        .name(createCustomerRequest.getName()).build()));

        return customerMapper.mapToDto(customer);
    }
}
