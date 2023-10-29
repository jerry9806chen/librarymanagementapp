package com.example.librarymanagementapp.daos;

import java.sql.SQLException;
import java.util.List;

import com.example.librarymanagementapp.entity.Genre;

/**
 * @author Jerry Chen
 * 
 */
public interface GenreDao {
	// Create Operation
	Integer createGenre(Genre genre) throws SQLException;
	
	// Read/Retrieve Operations
	Genre findGenreById(Long id) throws SQLException;
	Genre findGenreByName(String name) throws SQLException;
	List<Genre> findAllGenres() throws SQLException;
	
	// Update Operations
	Integer updateGenre(Genre genre) throws SQLException;
	
	// Delete Operations
	Integer deleteGenreById(Long id) throws SQLException;
	Integer deleteGenreByName(String name) throws SQLException;
	Integer deleteGenre(Genre genre) throws SQLException;
}
