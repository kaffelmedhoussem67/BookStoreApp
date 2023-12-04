package com.example.bookstoreapp.restcontroller;


import com.example.bookstoreapp.entity.DTO.BookDto;
import com.example.bookstoreapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBook(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }


}
