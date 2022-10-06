package com.getir.demo.service;

import com.getir.demo.common.request.CreateCustomerRequest;
import com.getir.demo.dto.CustomerDto;

/**
 * CustomerService
 * Author: mcaylak
 * Since : 6.10.2022
 */
public interface CustomerService {

    CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest);

}
