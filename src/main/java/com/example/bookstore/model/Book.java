package com.example.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	// attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title, author, isbn;
	private int year;
	private double price;
	
	public Book() {
		super();
		this.title = "title";
		this.author = "author";
		this.year = 2022;
		this.isbn = "isbn";
		this.price = 123;
	}
	
	public Book(String title, String author, int year, String isbn, double price) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
	}
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
		this.year = 2022;
		this.isbn = "0123456789";
		this.price = 30;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
