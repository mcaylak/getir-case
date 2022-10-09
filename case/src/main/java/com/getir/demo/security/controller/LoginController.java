package com.getir.demo.security.controller;

import com.getir.demo.common.request.LoginCustomerRequest;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController
 * Author: mcaylak
 * Since : 6.10.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/login")
public class LoginController {

    private AuthService authService;

    @PostMapping
    public ResponseBean<LoginResponse> login(@RequestBody LoginCustomerRequest loginCustomerRequest) {
        return new ResponseBean<>(authService.customerLogin(loginCustomerRequest));
    }


}
