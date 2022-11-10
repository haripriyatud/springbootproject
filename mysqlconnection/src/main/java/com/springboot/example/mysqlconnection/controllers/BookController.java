package com.springboot.example.mysqlconnection.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.example.mysqlconnection.ResourceNotFoundException;
import com.springboot.example.mysqlconnection.model.Book;
import com.springboot.example.mysqlconnection.repository.BookRepository;


	@Controller
	@RequestMapping(path="/books") 
	public class BookController {
		
		 @Bean
		    public WebMvcConfigurer corsConfigurer() {
		        return new WebMvcConfigurer() {
		            @Override
		            public void addCorsMappings(CorsRegistry registry) {
		                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");


		            }
		        };
		    }
			
		 @Autowired 
		 private BookRepository bookrepository;
			
			@CrossOrigin()
			  @GetMapping(path="/all")
			  public ResponseEntity<List<Book>> getAllBooks() {
			      List<Book> books = new ArrayList<Book>();

		        bookrepository.findAll().forEach(books::add);

			      return new ResponseEntity<>(books, HttpStatus.OK);
			  }
				 
			
			@CrossOrigin()
			  @GetMapping(path= "/book/{isbn}")
			  ResponseEntity<Book> one(@PathVariable BigInteger isbn) {			  
			        Optional <Book> book = bookrepository.findById(isbn);
			        
			        if (book.isPresent()) {
			            return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
			          } else {
			            throw new ResourceNotFoundException("Book with "+ isbn + " not found in the database");
			          }
			        }


}
