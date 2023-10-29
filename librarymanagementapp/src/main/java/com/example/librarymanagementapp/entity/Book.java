package com.example.librarymanagementapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Jerry Chen
 * 
 */
@Entity
@Table(name="Books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name = "isbn")
    private String isbn;
	@Column(name = "name")
    private String name;
	@Column(name = "author")
    private String author;
	@Column(name = "genre")
	private String genre;
	@Column(name = "borrower")
	private String borrower;
	@Column(name = "serialName")
    private String serialName;
	@Column(name = "description")
    private String description; 
	
	public Book() {
		super();
	}
	
	public Book(String name) {
		super();
		this.name = name;
	}
	
	public Book(String isbn, String name, String author, String genre, String borrower, String serialName, String description) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.serialName = serialName;
		this.borrower = borrower;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String string) {
		this.author = string;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
