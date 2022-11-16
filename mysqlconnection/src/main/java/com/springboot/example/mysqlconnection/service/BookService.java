package com.springboot.example.mysqlconnection.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.example.mysqlconnection.exceptions.InvalidRequestException;
import com.springboot.example.mysqlconnection.exceptions.ResourceNotFoundException;
import com.springboot.example.mysqlconnection.model.Book;
import com.springboot.example.mysqlconnection.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookrepository;

	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<Book>();
		

		bookrepository.findAll().forEach(books::add);

		return books ;
	}

	public Optional <Book> getonebookByIsbn(BigInteger isbn) {
		Optional<Book> book = bookrepository.findById(isbn);

		if (book.isPresent()) {
			return book;
		} else {
			throw new ResourceNotFoundException("Book with " + isbn + " not found in the database");
		}
	}

	public List<Book> getAllbookByAuthor(String Author) {

		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByAuthor(Author).forEach(books::add);

		if (books.isEmpty())
			throw new ResourceNotFoundException("No Books with " + Author + " not found in the database");

		return books;
	}

	public List<Book> getAllBooksOrderedByAuthor() {
		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByOrderByAuthor().forEach(books::add);

		return books;
	}

	public List<Book> getAllbookByPublisher(String Publisher) {

		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByPublisher(Publisher).forEach(books::add);

		if (books.isEmpty())
			throw new ResourceNotFoundException("No Books with " + Publisher + " not found in the database");

		return books;
	}

	public List<Book> getAllbookByAuthorAndPublisher(String Author, String Publisher) {

		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByAuthorAndPublisher(Author, Publisher).forEach(books::add);

		if (books.isEmpty())
			throw new ResourceNotFoundException(
					"No Books with " + Author + " and " + Publisher + " not found in the database");
		return books;

	}
	
	
	public Book postinBooks(Optional<Book> book) {
		Book newbook = null;
		if(book.isEmpty()) {
			throw new InvalidRequestException("Request is not valid");
			}
		
		else  {
	      newbook= bookrepository.save(book.get());						
		}
		return newbook;
	}
	
	
	  public String deleteEmployee(BigInteger isbn) {
		  Optional<Book> book = bookrepository.findById(isbn);

			if (book.isPresent()) {
				  bookrepository.deleteById(isbn);
				  return "Book deleted sucessfully from database";

			} else {
				throw new ResourceNotFoundException("Book with " + isbn + " not found in the database");
			}
	  }
	

}
