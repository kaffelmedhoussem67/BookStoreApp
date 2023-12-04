package com.example.bookstoreapp.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Nonnull
    private String title;

    @Nonnull
    private Double price;

    @Nonnull
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Nonnull
    private LocalDate releaseDate;

    private Double rate;


}
