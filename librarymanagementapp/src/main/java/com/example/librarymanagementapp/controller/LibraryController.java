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

import com.example.librarymanagementapp.entity.LibraryBranch;
import com.example.librarymanagementapp.services.LibraryBranchService;

/**
 * @author Jerry Chen
 * 
 */
@RestController
@RequestMapping("/api/library-branches")
public class LibraryController {
    private LibraryBranchService libraryBranchService;

    @Autowired
    public LibraryController(LibraryBranchService libraryBranchService) {
        this.libraryBranchService = libraryBranchService;
    }

    @GetMapping()
    public ResponseEntity<List<LibraryBranch>> GetAllLibrary(@RequestParam(required = false) String library) {
    	try {
	    	List<LibraryBranch> branches = libraryBranchService.getAllLibraryBranchs(library);
	    	if (branches.isEmpty())
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(libraryBranchService.getAllLibraryBranchs(library));
	        return ResponseEntity.status(HttpStatus.OK).body(libraryBranchService.getAllLibraryBranchs(library));
	    } catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryBranch> GetByid(@PathVariable Long id) {
    	try {
	        LibraryBranch libraryBranch = libraryBranchService.findById(id);
	        if(libraryBranch != null)
	            return ResponseEntity.ok(libraryBranch);
	        else
	            return ResponseEntity.noContent().build();
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
        
    }

    @PostMapping()
    public ResponseEntity<LibraryBranch> addLibraryBranch(LibraryBranch libraryBranch) {
    	try {
	        libraryBranch = libraryBranchService.addLibraryBranch(libraryBranch);
	        return ResponseEntity.status(HttpStatus.CREATED).body(libraryBranch);
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryBranch> UpdateLibrary(@PathVariable Long id, LibraryBranch libraryBranch) {
    	try {
	        libraryBranch = libraryBranchService.updateLibraryBranch(id, libraryBranch);
	        if (libraryBranch != null)
	            return ResponseEntity.ok(libraryBranch);
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteLibrary(@PathVariable Long id) {
    	try {
	        LibraryBranch libraryBranch = libraryBranchService.deleteLibraryBranch(id);
	        if (libraryBranch != null)
	            return ResponseEntity.noContent().build();
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }
}
