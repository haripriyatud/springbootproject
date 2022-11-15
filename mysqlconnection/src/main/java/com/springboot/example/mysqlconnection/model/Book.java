package com.springboot.example.mysqlconnection.model;

import java.math.BigInteger;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Book {

	@Id
	private BigInteger isbn;

	private String title;

	private String subtitle;

	private String author;

	private String publisher;

	private String description;

	private String website;
	
	public Book() {
	}

	public Book(BigInteger isbn, String title, String subtitle, String author, String publisher, String description,
			String website) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;
		this.author = author;
		this.publisher = publisher;
		this.description = description;
		this.website = website;
	}
	
	public static Comparator<Book> authorComparator = new Comparator<Book>() {
		  
        // Comparing attributes of students
        public int compare(Book b1, Book b2) {
            String author1
                = b1.getAuthor().toUpperCase();
            String author2
                = b2.getAuthor().toUpperCase();
  
            // Returning in ascending order
            return author1.compareTo(
                       author2);
        }
    };
	
	public BigInteger getIsbn() {
		return isbn;
	}

	public void setIsbn(BigInteger isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
