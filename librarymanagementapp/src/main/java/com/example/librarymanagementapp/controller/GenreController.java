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
import com.example.librarymanagementapp.entity.Genre;
import com.example.librarymanagementapp.services.GenreService;

/**
 * @author Jerry Chen
 * 
 */
@RestController
@RequestMapping("/api/genre")
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    public ResponseEntity<List<Genre>> getAllGenres(@RequestParam(required = false) String genre) {
    	try {
	    	List<Genre> genres = genreService.getAllGenres(genre);
	    	if (genres.isEmpty())
	    		ResponseEntity.status(HttpStatus.NO_CONTENT).body(genres);
	        return ResponseEntity.status(HttpStatus.OK).body(genreService.getAllGenres(genre));
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getByid(@PathVariable Long id) {
    	try {
	        Genre genre = genreService.findById(id);
	        if(genre != null)
	            return ResponseEntity.ok(genre);
	        else
	            return ResponseEntity.noContent().build();
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PostMapping()
    public ResponseEntity<Genre> addGenre(Genre genre) {
	    try {
	        genre = genreService.addGenre(genre);
	        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, Genre genre) {
    	try {
	        genre = genreService.updateGenre(id, genre);
	        if (genre != null)
	            return ResponseEntity.ok(genre);
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
    	try {
	        Genre genre = genreService.deleteGenre(id);
	        if (genre != null)
	            return ResponseEntity.noContent().build();
	        else
	            return ResponseEntity.notFound().build();
    	} catch (SQLException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    }
}
