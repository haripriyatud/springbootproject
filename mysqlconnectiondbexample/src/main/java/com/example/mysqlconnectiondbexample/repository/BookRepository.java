package com.example.mysqlconnectiondbexample.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mysqlconnectiondbexample.model.Book;

@Repository

public interface BookRepository extends JpaRepository<Book, BigInteger>{

}
