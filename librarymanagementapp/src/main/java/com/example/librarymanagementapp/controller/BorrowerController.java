package com.example.librarymanagementapp.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementapp.entity.Borrower;
import com.example.librarymanagementapp.services.BorrowerService;

/**
 * @author Jerry Chen
 * 
 */
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping()
    public ResponseEntity<List<Borrower>> getAllBorrowers(@RequestParam(required = false) String borrower) {
    	try {
	    	List<Borrower> borrowers = borrowerService.getAllBorrowers(borrower);
	    	if (borrowers.isEmpty())
	    		ResponseEntity.status(HttpStatus.NO_CONTENT).body(borrowers);
	        return ResponseEntity.status(HttpStatus.OK).body(borrowers);
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getByid(@PathVariable Long id) {
    	try {
	        Borrower borrower = borrowerService.findById(id);
	        if(borrower != null)
	            return ResponseEntity.ok(borrower);
	        else
	            return ResponseEntity.noContent().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PostMapping()
    public ResponseEntity<Borrower> addBorrower(Borrower borrower) {
	    try {
	        borrower = borrowerService.addBorrower(borrower);
	        return ResponseEntity.status(HttpStatus.CREATED).body(borrower);
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrower> updateBorrower(@PathVariable Long id, Borrower borrower) {
        try {
	    	borrower = borrowerService.updateBorrower(id, borrower);
	        if (borrower != null)
	            return ResponseEntity.ok(borrower);
	        else
	            return ResponseEntity.notFound().build();
        } catch (SQLException exception) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) {
    	try {
	        Borrower borrower = borrowerService.deleteBorrower(id);
	        if (borrower != null)
	            return ResponseEntity.noContent().build();
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }
}
