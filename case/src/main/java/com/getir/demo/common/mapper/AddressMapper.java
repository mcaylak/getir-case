package com.getir.demo.common.mapper;

import com.getir.demo.dto.AddressDto;
import com.getir.demo.entity.Address;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AddressMapper
 * Author: mcaylak
 * Since : 7.10.2022
 */

@Component
@AllArgsConstructor
public class AddressMapper {

    private final ModelMapper mapper;

    public AddressDto mapToDto(Address entity) {
        return this.mapper.map(entity, AddressDto.class);
    }

    public Address mapToEntity(AddressDto dto) {
        return this.mapper.map(dto, Address.class);
    }

    public List<Address> map2EntityList(List<AddressDto> dtoList) {
        return dtoList.stream().map(this::map2Entity).collect(Collectors.toList());
    }

    public List<AddressDto> map2DtoList(List<Address> entityList) {
        return entityList.stream().map(this::map2Dto).collect(Collectors.toList());
    }

    private Address map2Entity(AddressDto dto) {
        return this.mapper.map(dto, Address.class);
    }

    private AddressDto map2Dto(Address entity) {
        return this.mapper.map(entity, AddressDto.class);
    }

}
