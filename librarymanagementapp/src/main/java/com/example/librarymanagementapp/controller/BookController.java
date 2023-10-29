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
import com.example.librarymanagementapp.entity.Book;
import com.example.librarymanagementapp.services.BookService;

/**
 * @author Jerry Chen
 * 
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
    	try {
	    	List<Book> books = bookService.findAllBooks(title, author);
	    	if (books.isEmpty())
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(books);
	        return ResponseEntity.status(HttpStatus.OK).body(books);
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getByid(@PathVariable Long id)
    {
    	try {
	        Book book = bookService.findById(id);
	        if(book != null)
	            return ResponseEntity.ok(book);
	        else
	            return ResponseEntity.noContent().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PostMapping()
    public ResponseEntity<Book> addBook(Book book) {
    	try {
	        book = bookService.addBook(book);
	        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, Book book) {
    	try {
	        book = bookService.updateBook(id, book);
	        if (book != null)
	            return ResponseEntity.ok(book);
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    	try {
	        Book book = bookService.deleteBook(id);
	        if (book != null)
	            return ResponseEntity.noContent().build();
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }
}
