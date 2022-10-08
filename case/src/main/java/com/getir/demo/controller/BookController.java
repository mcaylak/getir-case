package com.getir.demo.controller;

import com.getir.demo.common.request.BookCreateRequest;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.BookDto;
import com.getir.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * BookController
 * Author: mcaylak
 * Since : 7.10.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/book/")
public class BookController {

    private BookService bookService;

    @PostMapping("create")
    public ResponseBean<BookDto> createCustomer(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        return new ResponseBean<>(bookService.createBook(bookCreateRequest));
    }

    @PostMapping("updateStock")
    public ResponseBean<BookDto> updateBookStock(@RequestParam(required = true) Long bookId,
                                                 @RequestParam(required = true) Long bookStock) {
        return new ResponseBean<>(bookService.updateBookStock(bookId, bookStock));
    }

}
