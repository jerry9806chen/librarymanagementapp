package com.example.librarymanagementapp.services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementapp.repository.BookRepository;
import com.example.librarymanagementapp.entity.Book;

/**
 * @author Jerry Chen
 * 
 */
@Service
public class BookService {
    private final BookRepository bookRepository;
    Logger logger = LoggerFactory.getLogger(BookService.class);
	
    @Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAllBooks(String title, String author) throws SQLException {
        try {
            return bookRepository.findAllBooks();
        } catch (Exception ex) {
            logger.error("BookService Error: findAllBooks " + ex);
            throw ex;
        }
    }
	
	public Book findById(Long id) throws SQLException {
        try {
            return bookRepository.findBookById(id);
        } catch (Exception ex) {
            logger.error("BookService Error: findById " + ex);
            throw ex;
        }
    }

    public Book addBook(Book book) throws SQLException {
        try {
        	bookRepository.createBook(book);
            return book;
        } catch (Exception ex) {
            logger.error("BookService Error: addBook " + ex);
            throw ex;
        }
    }

    public Book updateBook(Long id, Book book) throws SQLException {
        try {
            Book bookDetails = bookRepository.findBookById(id);
            if (bookDetails != null) {
                bookDetails.setIsbn(book.getIsbn());
                bookDetails.setName(book.getName());
                bookDetails.setAuthor(book.getAuthor());
                bookDetails.setGenre(book.getGenre());
                bookDetails.setBorrower(book.getBorrower());
                bookDetails.setSerialName(book.getSerialName());
                bookDetails.setDescription(book.getDescription());
                bookRepository.updateBook(bookDetails);
                return bookDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("BookService Error: updateBook " + ex);
            throw ex;
        }
    }

    public Book deleteBook(Long id) throws SQLException {
        try {
            Book bookDetails = bookRepository.findBookById(id);
            if (bookDetails != null) {
                bookRepository.deleteBookById(id);
                return bookDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("BookService Error: deleteBook " + ex);
            throw ex;
        }
    }
}
