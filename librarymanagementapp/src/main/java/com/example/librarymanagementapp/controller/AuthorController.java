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

import com.example.librarymanagementapp.entity.Author;
import com.example.librarymanagementapp.services.AuthorService;

/**
 * @author Jerry Chen
 * 
 */
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false) String author) {
    	try {
    		List<Author> authors = authorService.getAllAuthors();
    		if (authors.isEmpty())
        		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(authors);
            return ResponseEntity.status(HttpStatus.OK).body(authors);
    	} catch(SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getByid(@PathVariable Long id)
    {
    	try {
	        Author author = authorService.getAuthorById(id);
	        if(author != null)
	            return ResponseEntity.ok(author);
	        else
	            return ResponseEntity.noContent().build();
    	} catch(SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PostMapping()
    public ResponseEntity<Author> addAuthor(Author author) {
    	try {
	        author = authorService.addAuthor(author);
	        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, Author author) {
    	try {
	        author = authorService.updateAuthor(id, author);
	        if (author != null)
	            return ResponseEntity.ok(author);
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id)
    {
    	try {
	        Author author = authorService.deleteAuthor(id);
	        if (author != null)
	            return ResponseEntity.noContent().build();
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }
}
