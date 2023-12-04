package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.DTO.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    List<BookDto> booksToBookDtos(List<Book> books);

    BookDto bookToBookDTO(Book book);

    Book bookDTOToBook(BookDto bookDTO);
}
