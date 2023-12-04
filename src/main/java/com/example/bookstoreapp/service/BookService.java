package com.example.bookstoreapp.service;


import com.example.bookstoreapp.entity.DTO.BookDto;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    @Autowired
    private BookMapper bookMapper;


    public List<BookDto> findAllBooks(){
        return bookMapper.booksToBookDtos(bookRepository.findAll());
    }


}
