package com.springboot.example.mysqlconnection.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springboot.example.mysqlconnection.ResourceNotFoundException;
import com.springboot.example.mysqlconnection.model.Book;
import com.springboot.example.mysqlconnection.service.BookService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	public void getAllBooksTest() throws Exception {
		BigInteger isbn = new BigInteger("9781449331818");
		Book book = new Book(isbn, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "", "", "");
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book);
		when(bookService.getAllBooks()).thenReturn(booklist);
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/all"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(jsonPath("$[0].author", Matchers.equalTo("Addy Osmani")));
	}

	@Test
	public void getAllBooksByAuthor() throws Exception {
		BigInteger isbn = new BigInteger("9781449331818");
		Book book = new Book(isbn, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "", "", "");
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book);
		when(bookService.getAllbookByAuthor("Addy Osmani")).thenReturn(booklist);
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/bookbyauthor/Addy Osmani"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(jsonPath("$[0].author", Matchers.equalTo("Addy Osmani")));
	}

	@Test
	public void getAllBooksByAuthorException() throws Exception {
		BigInteger isbn = new BigInteger("9781449331818");
		Book book = new Book(isbn, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "", "", "");
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book);
		String unknown_author = "Addy Osman";
		String message = "No Books with " + unknown_author + " not found in the database";
		when(bookService.getAllbookByAuthor("Addy Osman")).thenThrow(new ResourceNotFoundException(message));
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/bookbyauthor/" + unknown_author))
				.andExpect(jsonPath("message", Matchers.equalTo(message))).andExpect(status().isNotFound());
	}
	
	@Test
	public void getAllBooksByPublisher() throws Exception {
		BigInteger isbn1 = new BigInteger("9781449331818");
		BigInteger isbn2 = new BigInteger("9781484200766");

		Book book1 = new Book(isbn1, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "OReilly Media", "", "");
		Book book2 = new Book(isbn2, "Pro Git", "Everything you neeed to know about Git",
				"Scott Chacon and Ben Straub", "Apress; 2nd edition", "", "");
		
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book1);
		booklist.add(book2);
		String publisher = "OReilly Media";
		when(bookService.getAllbookByPublisher("OReilly Media")).
		thenReturn(booklist.stream().filter(e->e.getPublisher().equals(publisher)).collect(Collectors.toList()));
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/bookbypublisher/"+publisher))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(jsonPath("$[0].author", Matchers.equalTo("Addy Osmani")));
	}
	
	@Test
	public void getAllBooksByPublisherException() throws Exception {
		BigInteger isbn1 = new BigInteger("9781449331818");
		BigInteger isbn2 = new BigInteger("9781484200766");

		Book book1 = new Book(isbn1, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "OReilly Media", "", "");
		Book book2 = new Book(isbn2, "Pro Git", "Everything you neeed to know about Git",
				"Scott Chacon and Ben Straub", "Apress; 2nd edition", "", "");
		
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book1);
		booklist.add(book2);
		String publisher = "OReilly Medi";
		String message = "No Books with " + publisher + " not found in the database";

		when(bookService.getAllbookByPublisher(publisher)).
		thenThrow(new ResourceNotFoundException(message));
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/bookbypublisher/"+publisher))
		.andExpect(jsonPath("message", Matchers.equalTo(message))).andExpect(status().isNotFound());

	}
	
	@Test
	public void getAllBooksByAuthorAndPublisher() throws Exception {
		BigInteger isbn1 = new BigInteger("9781449331818");
		BigInteger isbn2 = new BigInteger("9781484200766");

		Book book1 = new Book(isbn1, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "OReilly Media", "", "");
		Book book2 = new Book(isbn2, "Pro Git", "Everything you neeed to know about Git",
				"Scott Chacon and Ben Straub", "Apress; 2nd edition", "", "");
		
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book1);
		booklist.add(book2);
		String author="Addy Osmani" ;
		String publisher = "OReilly Media";
		
		when(bookService.getAllbookByAuthorAndPublisher(author, publisher)).
		thenReturn(booklist.stream().filter(e->e.getPublisher().equals(publisher) && e.getAuthor().equals(author))
				.collect(Collectors.toList()));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/bookbyauthorandpublisher/"+author+"/"+publisher))
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
		.andExpect(jsonPath("$[0].author", Matchers.equalTo("Addy Osmani")));
	}
	
	
	@Test
	public void getAllBooksOrderedByAuthorTest() throws Exception {
		BigInteger isbn1 = new BigInteger("9781449331818");
		BigInteger isbn2 = new BigInteger("9781484200766");
		Book book1 = new Book(isbn2, "Pro Git", "Everything you neeed to know about Git",
				"Scott Chacon and Ben Straub", "Apress; 2nd edition", "", "");

		Book book2 = new Book(isbn1, "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developers Guide",
				"Addy Osmani", "OReilly Media", "", "");

		
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book1);
		booklist.add(book2);
		String author="Addy Osmani" ;
		String publisher = "OReilly Media";
		
		when(bookService.getAllBooksOrderedByAuthor()).
		thenReturn(booklist.stream().sorted(Book.authorComparator).collect(Collectors.toList()));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/books/allOrderedByAuthor"))
		.andExpect(jsonPath("$[0].author", Matchers.equalTo("Addy Osmani")))
		.andExpect(jsonPath("$[1].author", Matchers.equalTo("Scott Chacon and Ben Straub")));

	}
	
	
	
}
