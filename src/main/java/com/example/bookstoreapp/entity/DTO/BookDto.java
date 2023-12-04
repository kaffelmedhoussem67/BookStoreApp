package com.example.bookstoreapp.entity.DTO;

import com.example.bookstoreapp.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String title;

    private Double price;

    private String author;

    private Genre genre;

    private LocalDate releaseDate;

    private Double rate;


}
