package com.springboot.example.mysqlconnection.controllers;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.springboot.example.mysqlconnection.model.Book;
import com.springboot.example.mysqlconnection.service.BookService;

@Controller
@RequestMapping(path = "/books")
public class BookController {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

	@Autowired
	private BookService bookservice;

	@CrossOrigin()
	@GetMapping(path = "/all")
	public ResponseEntity<List<Book>> getAllBooks() {
	 List<Book> books = bookservice.getAllBooks();
	  return new ResponseEntity<>(books, HttpStatus.OK);

	}

	@CrossOrigin()
	@GetMapping(path = "/bookbyisbn/{isbn}")
	ResponseEntity<Book> getonebookByIsbn(@PathVariable BigInteger isbn) {
		Optional<Book> book = bookservice.getonebookByIsbn(isbn);
		return new ResponseEntity<>(book.get(), HttpStatus.OK);
	}

	@CrossOrigin()
	@GetMapping(path = "/bookbyauthor/{Author}")
	ResponseEntity<List<Book>> getallByAuthorByAuthor(@PathVariable String Author) {
		 List<Book> books=  bookservice.getAllbookByAuthor(Author);
		 return new ResponseEntity<>(books, HttpStatus.OK);
		 
	}

	@CrossOrigin()
	@GetMapping(path = "/allOrderedByAuthor")
	public ResponseEntity<List<Book>> getAllBooksOrderedByAuthor() {
		 List<Book> books =  bookservice.getAllBooksOrderedByAuthor();
		  return new ResponseEntity<>(books, HttpStatus.OK);

	}

	@CrossOrigin()
	@GetMapping(path = "/bookbypublisher/{Publisher}")
	ResponseEntity<List<Book>> getallByPublisher(@PathVariable String Publisher) {
		 List<Book> books=  bookservice.getAllbookByPublisher(Publisher);
		 return new ResponseEntity<>(books, HttpStatus.OK);

	}

	@CrossOrigin()
	@GetMapping(path = "/bookbyauthorandpublisher/{Author}/{Publisher}")
	ResponseEntity<List<Book>> getallByAuthorByAuthorAndPublisher(@PathVariable String Author,
			@PathVariable String Publisher) {
		 List<Book> books=  bookservice.getAllbookByAuthorAndPublisher(Author, Publisher);
		 return new ResponseEntity<>(books, HttpStatus.OK);

	}
	
	@CrossOrigin()
	  @PostMapping(path="/save")
	  ResponseEntity<Book> saveBook(@RequestBody Optional <Book> book) {
	    Book newbook = bookservice.postinBooks(book);
		return new ResponseEntity<Book>(newbook, HttpStatus.CREATED);   
	  }

	@CrossOrigin()
	  @DeleteMapping(path="/delete/{isbn}")
	  ResponseEntity<Book> deleteBookByIsbn(@PathVariable BigInteger isbn) {
		bookservice.deleteEmployee(isbn);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);   
	  }
}
