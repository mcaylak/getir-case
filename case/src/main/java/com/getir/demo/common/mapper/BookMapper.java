package com.getir.demo.common.mapper;

import com.getir.demo.dto.BookDto;
import com.getir.demo.entity.Book;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BookMapper
 * Author: mcaylak
 * Since : 8.10.2022
 */

@Component
@AllArgsConstructor
public class BookMapper {

    private final ModelMapper mapper;

    public BookDto mapToDto(Book entity) {
        return this.mapper.map(entity, BookDto.class);
    }

    public Book mapToEntity(BookDto dto) {
        return this.mapper.map(dto, Book.class);
    }

    public List<Book> map2EntityList(List<BookDto> dtoList) {
        return dtoList.stream().map(this::map2Entity).collect(Collectors.toList());
    }

    public List<BookDto> map2DtoList(List<Book> entityList) {
        return entityList.stream().map(this::map2Dto).collect(Collectors.toList());
    }

    private Book map2Entity(BookDto dto) {
        return this.mapper.map(dto, Book.class);
    }

    private BookDto map2Dto(Book entity) {
        return this.mapper.map(entity, BookDto.class);
    }

}
