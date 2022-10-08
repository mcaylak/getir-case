package com.getir.demo.service.impl;

import com.getir.demo.common.mapper.AddressMapper;
import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.AddressDto;
import com.getir.demo.repository.AddressRepository;
import com.getir.demo.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * AddressServiceImpl
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    @Override
    @Transactional
    public AddressDto createAddress(PlaceOrderRequest orderRequest) {
        AddressDto addressDto = AddressDto.builder()
                .name(orderRequest.getName())
                .surname(orderRequest.getSurname())
                .fullAddress(orderRequest.getFullAddress())
                .country(orderRequest.getCountry())
                .city(orderRequest.getCity())
                .postalCode(orderRequest.getPostalCode())
                .build();

        return addressMapper.mapToDto(addressRepository.save(addressMapper.mapToEntity(addressDto)));
    }
}
