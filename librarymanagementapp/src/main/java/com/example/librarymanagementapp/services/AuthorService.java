package com.example.librarymanagementapp.services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementapp.entity.Author;
import com.example.librarymanagementapp.repository.AuthorRepository;

/**
 * @author Jerry Chen
 * 
 */
@Service
public class AuthorService {
	private final AuthorRepository authorRepository;
	Logger logger = LoggerFactory.getLogger(AuthorService.class);
	
	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public Author getAuthorByName(String author) throws SQLException
    {
        try {
            return authorRepository.findAuthorByName(author);
        } catch (Exception ex) {
            logger.error("AuthorService Error: getAuthorByName " + ex);
            throw ex;
        }
    }

	public List<Author> getAllAuthors() throws SQLException
    {
        try {
            return authorRepository.findAllAuthors();
        } catch (Exception ex) {
            logger.error("AuthorService Error: getAllAuthors " + ex);
            throw ex;
        }
    }

    public Author getAuthorById(Long id) throws SQLException {
        try {
            return authorRepository.findAuthorById(id);
        } catch (Exception ex) {
            logger.error("AuthorService Error: findById " + ex);
            throw ex;
        }
    }

    public Author addAuthor(Author author) throws SQLException {
        try {
        	authorRepository.createAuthor(author);
            return author;
        } catch (Exception ex) {
            logger.error("AuthorService Error: addAuthor " + ex);
            throw ex;
        }
    }

    public Author updateAuthor(Long id, Author author) throws SQLException {
        try {
            Author authorDetails = authorRepository.findAuthorById(id);
            if (authorDetails != null) {
                authorDetails.setName(author.getName());
                authorDetails.setDescription(author.getDescription());
                authorRepository.updateAuthor(authorDetails);
                return authorDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("AuthorService Error: updateAuthor " + ex);
            throw ex;
        }
    }

    public Author deleteAuthor(Long id) throws SQLException {
        try {
            Author authorDetails = authorRepository.findAuthorById(id);
            if (authorDetails != null) {
                authorRepository.deleteAuthorById(id);
                return authorDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("AuthorService Error: deleteAuthor " + ex);
            throw ex;
        }
    }
}
