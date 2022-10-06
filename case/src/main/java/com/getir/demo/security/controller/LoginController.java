package com.getir.demo.security.controller;

import com.getir.demo.common.request.LoginCustomerRequest;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.security.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * LoginController
 * Author: mcaylak
 * Since : 6.10.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/login")
public class LoginController {

    private LoginService loginService;

    @GetMapping
    public ResponseBean<LoginResponse> login(@Valid @RequestBody LoginCustomerRequest loginCustomerRequest) {
        return new ResponseBean<>(loginService.customerLogin(loginCustomerRequest));
    }


}
