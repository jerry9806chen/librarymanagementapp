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

import com.example.librarymanagementapp.daos.AuthorDao;
import com.example.librarymanagementapp.entity.Author;

import com.example.librarymanagementapp.utils.DatabaseConnection;

/**
 * @author Jerry Chen
 * 
 */
@Repository
public class AuthorRepository implements AuthorDao {
	private final Connection connection;
	
	@Autowired
	public AuthorRepository(DatabaseConnection connection) {
		this.connection = connection.getConnection();
	}
	
	/*@Query("SELECT * FROM Authors WHERE name LIKE %?1%")
	List<Author> searchByName(String author);

	@Query("SELECT * FROM Authors")
	List<Author> findAll();

	@Query("SELECT * FROM Authors WHERE id=%?1%")
	Optional<Author> findById(Long id);

	@Query("DELETE FROM Authors WHERE id=%?1%")
	void deleteById(Long id);

	@Query("INSERT INTO Authors (id, name, description) VALUES (%?1%,%?2%,%?3%)")
	Author addAuthor(Author author);

	@Query("UPDATE Authors SET name=%?2%, description=%?3% WHERE id=%?1%")
	Author updateAuthor(Author authorDetails);*/

	@Override
	@Query("INSERT INTO Authors (NAME, DESCRIPTION) VALUES (%?1%,%?2%,%?3%)")
	public Integer createAuthor(Author author) throws SQLException {
		String insertAuthorFormat = """
				INSERT INTO Authors (
				    NAME,
				    DESCRIPTION)
				VALUES (
				    ?, ?
				    )""";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorFormat);) {
			preparedStatement.setString(1, author.getName());
			preparedStatement.setString(2, author.getDescription());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("SELECT * FROM Authors WHERE ID=%?1%")
	public Author findAuthorById(Long id) throws SQLException {
		String getAuthorFormat = """
				SELECT *
				FROM
				    Authors
				WHERE
				    ID = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getAuthorFormat);) {
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Author author = new Author();
			if (rs.next()) {
				author.setId(rs.getLong("ID"));
				author.setName(rs.getString("NAME"));
				author.setDescription(rs.getString("DESCRIPTION"));
			}
			return author;
		}
	}

	@Override
	@Query("SELECT * FROM Authors WHERE NAME=%?1%")
	public Author findAuthorByName(String name) throws SQLException {
		String getAuthorFormat = """
				SELECT *
				FROM
				    Authors
				WHERE
				    NAME = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getAuthorFormat);) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Author author = new Author();
			if (rs.next()) {
				author.setId(rs.getLong("ID"));
				author.setName(rs.getString("NAME"));
				author.setDescription(rs.getString("DESCRIPTION"));
			}
			return author;
		}
	}

	@Override
	@Query("SELECT * FROM Authors")
	public List<Author> findAllAuthors() throws SQLException {
		String getAuthorFormat = """
				SELECT *
				FROM
				    Authors""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getAuthorFormat);) {
			ResultSet rs = preparedStatement.executeQuery();
			List<Author> authorList = new ArrayList<>();
			
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getLong("ID"));
				author.setName(rs.getString("NAME"));
				author.setDescription(rs.getString("DESCRIPTION"));
				
				authorList.add(author);
			}
			return authorList;
		}
	}

	@Override
	@Query("DELETE FROM Authors WHERE ID=%?1%")
	public Integer deleteAuthorById(Long id) throws SQLException {
		String deleteAuthorFormat = """
                DELETE FROM Authors
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAuthorFormat);) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Authors WHERE NAME=%?1%")
	public Integer deleteAuthorByName(String name) throws SQLException {
		String deleteAuthorFormat = """
                DELETE FROM Authors
                WHERE
                    NAME = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAuthorFormat);) {
			preparedStatement.setString(1, name);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Authors WHERE ID=%?1%")
	public Integer deleteAuthor(Author author) throws SQLException {
		String deleteAuthorFormat = """
                DELETE FROM Authors
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAuthorFormat);) {
			preparedStatement.setLong(1, author.getId());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("UPDATE Authors SET name=%?2%, description=%?3% WHERE id=%?1%")
	public Integer updateAuthor(Author author) throws SQLException {
		String updateAuthorFormat = """
				UPDATE Author
				SET
				    NAME = ?,
				    DESCRIPTION = ?
				WHERE
				    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateAuthorFormat);) {
			preparedStatement.setString(1, author.getName());
			preparedStatement.setString(2, author.getDescription());
			preparedStatement.setLong(3, author.getId());
			return preparedStatement.executeUpdate();
		}
	}

}
