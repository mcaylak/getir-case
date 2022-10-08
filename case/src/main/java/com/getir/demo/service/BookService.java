package com.getir.demo.service;

import com.getir.demo.common.request.BookCreateRequest;
import com.getir.demo.dto.BookDto;

/**
 * BookService
 * Author: mcaylak
 * Since : 6.10.2022
 */
public interface BookService {
    BookDto createBook(BookCreateRequest bookCreateRequest);

    BookDto updateBookStock(Long bookId, Long stock);

    BookDto reduceStock(Long bookId, Long reduceStock);

    Long getBookStockByBookId(Long bookId);
}
