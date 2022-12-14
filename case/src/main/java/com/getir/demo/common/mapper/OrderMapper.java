package com.getir.demo.common.mapper;

import com.getir.demo.dto.OrderDto;
import com.getir.demo.dto.OrderEntryDto;
import com.getir.demo.entity.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMapper
 * Author: mcaylak
 * Since : 7.10.2022
 */

@Component
@AllArgsConstructor
public class OrderMapper {

    private final ModelMapper mapper;
    private final OrderEntryMapper orderEntryMapper;

    public OrderDto mapToDto(Order entity) {
        List<OrderEntryDto> orderEntryDtoList = new ArrayList<>();
        if (entity.getOrderEntries() != null) {
            orderEntryDtoList = orderEntryMapper.map2DtoList(entity.getOrderEntries());
        }
        OrderDto orderDto = this.mapper.map(entity, OrderDto.class);
        orderDto.setOrderEntryDtoList(orderEntryDtoList);
        return orderDto;
    }

    public Order mapToEntity(OrderDto dto) {
        return this.mapper.map(dto, Order.class);
    }

    public List<Order> map2EntityList(List<OrderDto> dtoList) {
        return dtoList.stream().map(this::map2Entity).collect(Collectors.toList());
    }

    public List<OrderDto> map2DtoList(List<Order> entityList) {
        return entityList.stream().map(this::map2Dto).collect(Collectors.toList());
    }

    private Order map2Entity(OrderDto dto) {
        return this.mapper.map(dto, Order.class);
    }

    private OrderDto map2Dto(Order entity) {
        return this.mapper.map(entity, OrderDto.class);
    }

}
