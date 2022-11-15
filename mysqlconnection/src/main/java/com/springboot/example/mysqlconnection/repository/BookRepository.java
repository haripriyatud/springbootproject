package com.springboot.example.mysqlconnection.repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.example.mysqlconnection.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, BigInteger> {
	List<Book> findAllByAuthor(String Author);

	List<Book> findAllByOrderByAuthor();

	List<Book> findAllByPublisher(String Publisher);

	List<Book> findAllByAuthorAndPublisher(String Author, String Publisher);
}
