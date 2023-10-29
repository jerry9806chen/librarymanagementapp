package com.example.librarymanagementapp.daos;

import java.sql.SQLException;
import java.util.List;

import com.example.librarymanagementapp.entity.Author;

/**
 * @author Jerry Chen
 * 
 */
public interface AuthorDao {
	// Create Operation
	Integer createAuthor(Author author) throws SQLException;
	
	// Read/Retrieve Operations
	Author findAuthorById(Long id) throws SQLException;
	Author findAuthorByName(String name) throws SQLException;
	List<Author> findAllAuthors() throws SQLException;
	
	// Update Operations
	Integer updateAuthor(Author author) throws SQLException;
	
	// Delete Operations
	Integer deleteAuthorById(Long id) throws SQLException;
	Integer deleteAuthorByName(String name) throws SQLException;
	Integer deleteAuthor(Author author) throws SQLException;
}
