package com.example.librarymanagementapp.daos;

import java.sql.SQLException;
import java.util.List;

import com.example.librarymanagementapp.entity.Book;

/**
 * @author Jerry Chen
 * 
 */
public interface BookDao {
	// Create Operation
	Integer createBook(Book book) throws SQLException;
	
	// Read/Retrieve Operations
	Book findBookById(Long id) throws SQLException;
	Book findBookByName(String name) throws SQLException;
	Book findBookByISBN(String isbn) throws SQLException;
	Book findBookBySerialName(String serialName) throws SQLException;
	List<Book> findAllBooks() throws SQLException;
	
	// Update Operations
	Integer updateBook(Book book) throws SQLException;
	
	// Delete Operations
	Integer deleteBookById(Long id) throws SQLException;
	Integer deleteBookByName(String name) throws SQLException;
	Integer deleteBookByISBN(String isbn) throws SQLException;
	Integer deleteBookBySerialName(String serialName) throws SQLException;
	Integer deleteBook(Book book) throws SQLException;
}
