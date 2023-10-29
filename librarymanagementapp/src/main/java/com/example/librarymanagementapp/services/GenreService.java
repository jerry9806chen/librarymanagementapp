package com.example.librarymanagementapp.services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementapp.entity.Genre;
import com.example.librarymanagementapp.repository.GenreRepository;

/**
 * @author Jerry Chen
 * 
 */
@Service
public class GenreService {
	private final GenreRepository genreRepository;
	Logger logger = LoggerFactory.getLogger(GenreService.class);
	
	@Autowired
	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	public List<Genre> getAllGenres(String genre) throws SQLException
    {
        try {
            return genreRepository.findAllGenres();
        } catch (Exception ex) {
            logger.error("GenreService Error: getAllGenres " + ex);
            throw ex;
        }
    }

    public Genre findById(Long id) throws SQLException {
        try {
            return genreRepository.findGenreById(id);
        } catch (Exception ex) {
            logger.error("GenreService Error: findById " + ex);
            throw ex;
        }
    }

    public Genre addGenre(Genre genre) throws SQLException {
        try {
        	genreRepository.createGenre(genre);
            return genre;
        } catch (Exception ex) {
            logger.error("GenreService Error: addGenre " + ex);
            throw ex;
        }
    }

    public Genre updateGenre(Long id, Genre genre) throws SQLException {
        try {
            Genre genreDetails = genreRepository.findGenreById(id);
            if (genreDetails != null) {
                genreDetails.setName(genre.getName());
                genreDetails.setDescription(genre.getDescription());
                genreRepository.updateGenre(genreDetails);
                return genreDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("GenreService Error: updateGenre " + ex);
            throw ex;
        }
    }

    public Genre deleteGenre(Long id) throws SQLException {
        try {
            Genre genreDetails = genreRepository.findGenreById(id);
            if (genreDetails != null) {
                genreRepository.deleteGenreById(id);
                return genreDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("GenreService Error: deleteGenre " + ex);
            throw ex;
        }
    }
}
