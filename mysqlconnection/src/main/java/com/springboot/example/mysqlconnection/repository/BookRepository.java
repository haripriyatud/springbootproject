package com.springboot.example.mysqlconnection.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.example.mysqlconnection.model.Book;


@Repository

public interface BookRepository extends JpaRepository<Book, BigInteger>{

}
