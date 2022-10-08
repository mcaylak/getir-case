package com.getir.demo.service.impl;

import com.getir.demo.common.exception.IdNotFoundException;
import com.getir.demo.common.exception.InsufficientStockException;
import com.getir.demo.common.mapper.BookMapper;
import com.getir.demo.common.request.BookCreateRequest;
import com.getir.demo.dto.BookDto;
import com.getir.demo.entity.Book;
import com.getir.demo.repository.BookRepository;
import com.getir.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * BookServiceImpl
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Override
    public BookDto createBook(BookCreateRequest bookCreateRequest) {

        BookDto bookDto = BookDto.builder()
                .name(bookCreateRequest.getName())
                .price(bookCreateRequest.getPrice())
                .stock(bookCreateRequest.getStock())
                .description(bookCreateRequest.getDescription())
                .build();

        return bookMapper.mapToDto(bookRepository.save(bookMapper.mapToEntity(bookDto)));
    }

    @Override
    @Transactional
    public BookDto updateBookStock(Long bookId, Long stock) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            book.get().setStock(stock);
            return bookMapper.mapToDto(bookRepository.save(book.get()));
        }

        throw new IdNotFoundException("Book id " + bookId + " not found in database!");
    }

    @Override
    public Long getBookStockByBookId(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get().getStock();
        }

        throw new IdNotFoundException("Book id " + bookId + " not found in database!");
    }

    @Override
    @Transactional
    public BookDto reduceStock(Long bookId, Long reduceStock) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            Long currentStock = book.get().getStock();
            if (currentStock < reduceStock) {
                throw new InsufficientStockException("Insufficient stock!");
            }
            book.get().setStock(book.get().getStock() - reduceStock);
            return bookMapper.mapToDto(bookRepository.save(book.get()));
        }
        throw new IdNotFoundException("Book id " + bookId + " not found in database!");

    }
}
