package com.getir.demo.security.service;

import com.getir.demo.common.request.LoginCustomerRequest;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.entity.Customer;
import com.getir.demo.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * AuthService
 * Author: mcaylak
 * Since : 6.10.2022
 */
@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public LoginResponse customerLogin(LoginCustomerRequest request) {

        String username = request.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

        return LoginResponse.builder()
                .username(username)
                .token(jwtTokenProvider.createToken(username, null))
                .tokenType("Bearer")
                .build();
    }

    public Customer getCurrentUser() {
        return ((Customer) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

}
