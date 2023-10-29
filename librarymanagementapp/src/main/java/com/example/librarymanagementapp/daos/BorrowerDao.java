package com.example.librarymanagementapp.daos;

import java.sql.SQLException;
import java.util.List;

import com.example.librarymanagementapp.entity.Borrower;

/**
 * @author Jerry Chen
 * 
 */
public interface BorrowerDao {
	// Create Operation
	Integer createBorrower(Borrower borrower) throws SQLException;
	
	// Read/Retrieve Operations
	Borrower findBorrowerById(Long id) throws SQLException;
	Borrower findBorrowerByName(String name) throws SQLException;
	List<Borrower> findAllBorrowers() throws SQLException;
	
	// Update Operations
	Integer updateBorrower(Borrower borrower) throws SQLException;
	
	// Delete Operations
	Integer deleteBorrowerById(Long id) throws SQLException;
	Integer deleteBorrowerByName(String name) throws SQLException;
	Integer deleteBorrower(Borrower borrower) throws SQLException;
}
