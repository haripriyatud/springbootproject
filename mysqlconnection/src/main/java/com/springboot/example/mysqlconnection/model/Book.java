package com.springboot.example.mysqlconnection.model;


import java.math.BigInteger;

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
