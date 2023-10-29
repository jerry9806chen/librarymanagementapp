package com.example.librarymanagementapp.daos;

import java.sql.SQLException;
import java.util.List;

import com.example.librarymanagementapp.entity.LibraryBranch;

/**
 * @author Jerry Chen
 * 
 */
public interface LibraryBranchDao {
	// Create Operation
	Integer createLibraryBranch(LibraryBranch libraryBranch) throws SQLException;
	
	// Read/Retrieve Operations
	LibraryBranch findLibraryBranchById(Long id) throws SQLException;
	LibraryBranch findLibraryBranchByName(String name) throws SQLException;
	List<LibraryBranch> findAllLibraryBranchs() throws SQLException;
	
	// Update Operations
	Integer updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException;
	
	// Delete Operations
	Integer deleteLibraryBranchById(Long id) throws SQLException;
	Integer deleteLibraryBranchByName(String name) throws SQLException;
	Integer deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException;
}
