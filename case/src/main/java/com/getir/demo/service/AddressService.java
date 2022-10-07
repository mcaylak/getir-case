package com.getir.demo.service;

import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.AddressDto;

/**
 * AddressService
 * Author: mcaylak
 * Since : 6.10.2022
 */
public interface AddressService {
    AddressDto createAddress(PlaceOrderRequest orderRequest);
}
