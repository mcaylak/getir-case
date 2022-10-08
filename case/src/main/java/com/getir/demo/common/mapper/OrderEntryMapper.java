package com.getir.demo.common.mapper;

import com.getir.demo.dto.OrderEntryDto;
import com.getir.demo.entity.OrderEntry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderEntryEntryMapper
 * Author: mcaylak
 * Since : 8.10.2022
 */

@Component
@AllArgsConstructor
public class OrderEntryMapper {

    private final ModelMapper mapper;

    public OrderEntryDto mapToDto(OrderEntry entity) {
        return this.mapper.map(entity, OrderEntryDto.class);
    }

    public OrderEntry mapToEntity(OrderEntryDto dto) {
        return this.mapper.map(dto, OrderEntry.class);
    }

    public List<OrderEntry> map2EntityList(List<OrderEntryDto> dtoList) {
        return dtoList.stream().map(this::map2Entity).collect(Collectors.toList());
    }

    public List<OrderEntryDto> map2DtoList(List<OrderEntry> entityList) {
        return entityList.stream().map(this::map2Dto).collect(Collectors.toList());
    }

    private OrderEntry map2Entity(OrderEntryDto dto) {
        return this.mapper.map(dto, OrderEntry.class);
    }

    private OrderEntryDto map2Dto(OrderEntry entity) {
        return this.mapper.map(entity, OrderEntryDto.class);
    }

}
