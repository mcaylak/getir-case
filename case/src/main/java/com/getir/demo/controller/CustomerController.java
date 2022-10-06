package com.getir.demo.controller;

import com.getir.demo.common.request.CreateCustomerRequest;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.CustomerDto;
import com.getir.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * CustomerController
 * Author: mcaylak
 * Since : 6.10.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/customer/")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("create")
    public ResponseBean<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return new ResponseBean<>(customerService.createCustomer(createCustomerRequest));
    }

}
