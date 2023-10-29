package com.example.librarymanagementapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementapp.daos.GenreDao;
import com.example.librarymanagementapp.entity.Genre;
import com.example.librarymanagementapp.utils.DatabaseConnection;

/**
 * @author Jerry Chen
 * 
 */
@Repository
public class GenreRepository implements GenreDao {
	private final Connection connection;
	
	@Autowired
	public GenreRepository(DatabaseConnection connection) {
		this.connection = connection.getConnection();
	}
	
	/*@Query("SELECT * FROM Genres WHERE name LIKE %?1%")
	List<Genre> search(String genre);

	@Query("SELECT * FROM Genres")
	List<Genre> findAll();

	@Query("SELECT * FROM Genres WHERE id=%?1%")
	Optional<Genre> findById(Long id);

	@Query("DELETE FROM Genres WHERE id=%?1%")
	void deleteById(Long id);

	@Query("INSERT INTO Genres (name, description) VALUES (%?1%,%?2%)")
	Genre addGenre(Genre book);

	@Query("UPDATE Genres SET name=%?2%, description=%?3% WHERE id=%?1%")
	Genre updateGenre(Genre bookDetails);*/

	@Override
	@Query("INSERT INTO Genres (NAME, DESCRIPTION) VALUES (%?1%,%?2%,%?3%)")
	public Integer createGenre(Genre genre) throws SQLException {
		String insertGenreFormat = """
				INSERT INTO Genres (
				    NAME,
				    DESCRIPTION)
				VALUES (
				    ?, ?
				    )""";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertGenreFormat);) {
			preparedStatement.setString(1, genre.getName());
			preparedStatement.setString(2, genre.getDescription());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("SELECT * FROM Genres WHERE ID=%?1%")
	public Genre findGenreById(Long id) throws SQLException {
		String getGenreFormat = """
				SELECT *
				FROM
				    Genres
				WHERE
				    ID = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getGenreFormat);) {
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Genre genre = new Genre();
			if (rs.next()) {
				genre.setId(rs.getInt("ID"));
				genre.setName(rs.getString("NAME"));
				genre.setDescription(rs.getString("DESCRIPTION"));
			}
			return genre;
		}
	}

	@Override
	@Query("SELECT * FROM Genres WHERE NAME=%?1%")
	public Genre findGenreByName(String name) throws SQLException {
		String getGenreFormat = """
				SELECT *
				FROM
				    Genres
				WHERE
				    NAME = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getGenreFormat);) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Genre genre = new Genre();
			if (rs.next()) {
				genre.setId(rs.getInt("ID"));
				genre.setName(rs.getString("NAME"));
				genre.setDescription(rs.getString("DESCRIPTION"));
			}
			return genre;
		}
	}

	@Override
	@Query("SELECT * FROM Genres")
	public List<Genre> findAllGenres() throws SQLException {
		String getGenreFormat = """
				SELECT *
				FROM
				    Genres""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getGenreFormat);) {
			ResultSet rs = preparedStatement.executeQuery();
			List<Genre> genreList = new ArrayList<>();
			
			while (rs.next()) {
				Genre genre = new Genre();
				genre.setId(rs.getInt("ID"));
				genre.setName(rs.getString("NAME"));
				genre.setDescription(rs.getString("DESCRIPTION"));
				
				genreList.add(genre);
			}
			return genreList;
		}
	}

	@Override
	@Query("DELETE FROM Genres WHERE ID=%?1%")
	public Integer deleteGenreById(Long id) throws SQLException {
		String deleteGenreFormat = """
                DELETE FROM Genres
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteGenreFormat);) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Genres WHERE NAME=%?1%")
	public Integer deleteGenreByName(String name) throws SQLException {
		String deleteGenreFormat = """
                DELETE FROM Genres
                WHERE
                    NAME = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteGenreFormat);) {
			preparedStatement.setString(1, name);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Genres WHERE ID=%?1%")
	public Integer deleteGenre(Genre genre) throws SQLException {
		String deleteGenreFormat = """
                DELETE FROM Genres
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteGenreFormat);) {
			preparedStatement.setLong(1, genre.getId());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("UPDATE Genres SET name=%?2%, description=%?3% WHERE id=%?1%")
	public Integer updateGenre(Genre genre) throws SQLException {
		String updateGenreFormat = """
				UPDATE Genre
				SET
				    NAME = ?,
				    DESCRIPTION = ?
				WHERE
				    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateGenreFormat);) {
			preparedStatement.setString(1, genre.getName());
			preparedStatement.setString(2, genre.getDescription());
			preparedStatement.setLong(3, genre.getId());
			return preparedStatement.executeUpdate();
		}
	}
}
