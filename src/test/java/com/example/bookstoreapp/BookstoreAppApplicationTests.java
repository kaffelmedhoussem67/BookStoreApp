package com.example.bookstoreapp;

import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.DTO.BookDto;
import com.example.bookstoreapp.entity.Genre;
import com.example.bookstoreapp.mapper.BookMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@TestPropertySource("/application_test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookstoreAppApplicationTests {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Value("${sql.script.create.book}")
	private String sqlAddBook;

	public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

	@Value("${sql.script.delete.book}")
	private String sqlDeleteBook;

	@BeforeEach
	public void InsertData(){
		jdbcTemplate.execute(sqlAddBook);
	}

	@AfterEach
	public void DeleteData(){
     jdbcTemplate.execute(sqlDeleteBook);
	}
	@Test
	public void shouldBookToDTo(){
		Book book = new Book(1,"Lord of the ring",11.5, "Kaffel", Genre.Horror, LocalDate.of(2023,5,10), 1.5);
		BookDto bookDto = BookMapper.INSTANCE.bookToBookDTO(book);
		assertNotNull(bookDto);
		assertEquals(book.getRate(),bookDto.getRate());
		assertEquals(book.getAuthor(),bookDto.getAuthor());
		assertEquals(book.getTitle(),bookDto.getTitle());
		assertEquals(book.getReleaseDate(),bookDto.getReleaseDate());
		assertEquals(book.getPrice(),bookDto.getPrice());
		assertEquals(book.getGenre(),bookDto.getGenre());
	}

	@Test
	public void shouldBookToDTos() {
		Book book = new Book(1,"Lord of the ring",11.5, "Kaffel", Genre.Horror, LocalDate.of(2023,5,10), 1.5);
		Book book2 = new Book(2,"sadness and sorrow",12.0, "Kaffel", Genre.Drama, LocalDate.of(2023,6,11), 4.0);
		ArrayList<Book> books= new ArrayList<>();
		books.add(book);
		books.add(book2);
		ArrayList<BookDto> BooksDto =  new ArrayList<>(BookMapper.INSTANCE.booksToBookDtos(books));
		assertEquals(2,BooksDto.size());
	}

	@Test
	@WithMockUser(username = "test")
	public void displayAllBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")
						.header("Authorization",""))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$",hasSize(1)));

	}



}
