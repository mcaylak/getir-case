package com.example.demo.unit;

import com.getir.demo.common.mapper.AddressMapper;
import com.getir.demo.common.request.PlaceOrderRequest;
import com.getir.demo.dto.AddressDto;
import com.getir.demo.entity.Address;
import com.getir.demo.repository.AddressRepository;
import com.getir.demo.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * AddressServiceTest
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @Test
    void createAddress_returnSuccessfullyCreatedAddress_Test() {

        Address address = new Address();
        address.setId(1L);
        AddressDto addressDto = AddressDto.builder()
                .id(1L)
                .build();

        given(addressMapper.mapToDto(any(Address.class))).willReturn(addressDto);
        given(addressMapper.mapToEntity(any(AddressDto.class))).willReturn(address);
        given(addressRepository.save(any(Address.class))).willReturn(address);

        AddressDto createdAddress = addressService.createAddress(PlaceOrderRequest.builder().build());

        assertEquals(addressDto.getId(), createdAddress.getId());

    }
}
