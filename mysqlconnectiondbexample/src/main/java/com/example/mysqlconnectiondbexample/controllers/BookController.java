package com.example.mysqlconnectiondbexample.controllers;

import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.mysqlconnectiondbexample.ResourceNotFoundException;
import com.example.mysqlconnectiondbexample.model.Book;
import com.example.mysqlconnectiondbexample.repository.BookRepository;

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
		  public @ResponseBody Iterable<Book> getAllBooks() {
		    return bookrepository.findAll();
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

