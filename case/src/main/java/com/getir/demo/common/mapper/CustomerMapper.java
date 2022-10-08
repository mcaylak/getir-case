package com.getir.demo.common.mapper;

import com.getir.demo.dto.CustomerDto;
import com.getir.demo.entity.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CustomerMapper
 * Author: mcaylak
 * Since : 6.10.2022
 */
@Component
@AllArgsConstructor
public class CustomerMapper {
    private final ModelMapper mapper;

    public CustomerDto mapToDto(Customer entity) {
        CustomerDto customerDto = this.mapper.map(entity, CustomerDto.class);
        customerDto.setPassword(null);
        return customerDto;
    }

    public Customer mapToEntity(CustomerDto dto) {
        return this.mapper.map(dto, Customer.class);
    }

    public List<Customer> map2EntityList(List<CustomerDto> dtoList) {
        return dtoList.stream().map(this::map2Entity).collect(Collectors.toList());
    }

    public List<CustomerDto> map2DtoList(List<Customer> entityList) {
        return entityList.stream().map(this::map2Dto).collect(Collectors.toList());
    }

    private Customer map2Entity(CustomerDto dto) {
        return this.mapper.map(dto, Customer.class);
    }

    private CustomerDto map2Dto(Customer entity) {
        return this.mapper.map(entity, CustomerDto.class);
    }

}
