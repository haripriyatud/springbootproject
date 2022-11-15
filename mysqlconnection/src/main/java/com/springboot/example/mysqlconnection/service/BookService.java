package com.springboot.example.mysqlconnection.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.example.mysqlconnection.ResourceNotFoundException;
import com.springboot.example.mysqlconnection.model.Book;
import com.springboot.example.mysqlconnection.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookrepository;

	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = new ArrayList<Book>();

		bookrepository.findAll().forEach(books::add);

		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	public ResponseEntity<Book> getonebookByIsbn(BigInteger isbn) {
		Optional<Book> book = bookrepository.findById(isbn);

		if (book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Book with " + isbn + " not found in the database");
		}
	}

	public ResponseEntity<List<Book>> getAllbookByAuthor(String Author) {

		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByAuthor(Author).forEach(books::add);

		if (books.isEmpty())
			throw new ResourceNotFoundException("No Books with " + Author + " not found in the database");

		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	public ResponseEntity<List<Book>> getAllBooksOrderedByAuthor() {
		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByOrderByAuthor().forEach(books::add);

		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	public ResponseEntity<List<Book>> getAllbookByPublisher(String Publisher) {

		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByPublisher(Publisher).forEach(books::add);

		if (books.isEmpty())
			throw new ResourceNotFoundException("No Books with " + Publisher + " not found in the database");

		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	public ResponseEntity<List<Book>> getAllbookByAuthorAndPublisher(String Author, String Publisher) {

		List<Book> books = new ArrayList<Book>();

		bookrepository.findAllByAuthorAndPublisher(Author, Publisher).forEach(books::add);

		if (books.isEmpty())
			throw new ResourceNotFoundException(
					"No Books with " + Author + " and " + Publisher + " not found in the database");

		return new ResponseEntity<>(books, HttpStatus.OK);

	}

}
