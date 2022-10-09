package com.example.demo.unit;

import com.getir.demo.common.exception.IdNotFoundException;
import com.getir.demo.common.mapper.BookMapper;
import com.getir.demo.common.request.BookCreateRequest;
import com.getir.demo.dto.BookDto;
import com.getir.demo.entity.Book;
import com.getir.demo.repository.BookRepository;
import com.getir.demo.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * BookServiceTest
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    private Book book;
    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setName("Test book");
        book.setStock(10L);

        bookDto = BookDto.builder()
                .id(1L)
                .name("Test book")
                .stock(10L)
                .build();
    }

    @Test
    void createBook_successCreateBook_Test() {

        given(bookMapper.mapToDto(any(Book.class))).willReturn(bookDto);
        given(bookMapper.mapToEntity(any(BookDto.class))).willReturn(book);

        BookCreateRequest bookCreateRequest = prepareBookCreateRequest();
        given(bookRepository.save(any(Book.class))).willReturn(book);

        BookDto createdBook = bookService.createBook(bookCreateRequest);

        assertEquals(bookDto.getId(), createdBook.getId());

    }

    @Test
    void getBookStockByBookId_successResult_Test() {

        given(bookRepository.findById(book.getId())).willReturn(Optional.of(book));
        long id = book.getId();
        long returnedStock = bookService.getBookStockByBookId(id);

        assertEquals(book.getStock(), returnedStock);

    }

    @Test
    void getBookStockByBookId_throwIdNotFoundException_Test() {

        long id = 0;
        IdNotFoundException idNotFoundException = assertThrows(IdNotFoundException.class,
                () -> bookService.getBookStockByBookId(id), "Throwing exception");

        assertEquals("Book id 0 not found in database!", idNotFoundException.getMessage());

    }

    private BookCreateRequest prepareBookCreateRequest() {
        return BookCreateRequest.builder()
                .stock(10L)
                .price(100L)
                .name("Test Book")
                .description("Test Book Description")
                .build();
    }
}
