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

import com.example.librarymanagementapp.daos.BookDao;
import com.example.librarymanagementapp.entity.Book;
import com.example.librarymanagementapp.utils.DatabaseConnection;

/**
 * @author Jerry Chen
 * 
 */
@Repository
public class BookRepository implements BookDao {
	private final Connection connection;
	
	@Autowired
	public BookRepository(DatabaseConnection connection) {
		this.connection = connection.getConnection();
	}
	
	/*@Query("SELECT * FROM Books WHERE name LIKE %?1% OR author LIKE %?2%")
	List<Book> search(String title, String author);
	
	@Query("SELECT * FROM Books")
	List<Book> findAll();

	@Query("SELECT * FROM Books WHERE id=%?1%")
	Optional<Book> findById(Long id);

	@Query("DELETE FROM Books WHERE id=%?1%")
	void deleteById(Long id);

	@Query("INSERT INTO Books (isbn, name, author, genre, borrower, serialName, description) VALUES (%?1%,%?2%,%?3%,%?4%,%?5%,%?6%,%?7%)")
	Book addBook(Book book);

	@Query("UPDATE Books SET isbn=%?2%, name=%?3%, author=%?4%, genre=%?5%, borrower=%?6%, serialName=%?7%, description=%?8% WHERE id=%?1%")
	Book updateBook(Book bookDetails);*/

	@Override
	@Query("INSERT INTO Books (isbn, name, author, genre, borrower, serialName, description) VALUES (%?1%,%?2%,%?3%,%?4%,%?5%,%?6%,%?7%)")
	public Integer createBook(Book book) throws SQLException {
		String insertBookFormat = """
				INSERT INTO Books (
				    ISBN,
				    NAME,
				    AUTHOR,
				    GENRE,
				    BORROWER,
				    SERIALNAME,
				    DESCRIPTION)
				VALUES (
				    ?, ?, ?, ?, ?, ?, ?
				    )""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(insertBookFormat);) {
			preparedStatement.setString(1, book.getIsbn());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getGenre());
			preparedStatement.setString(5, book.getBorrower());
			preparedStatement.setString(6, book.getSerialName());
			preparedStatement.setString(7, book.getDescription());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("SELECT * FROM Books WHERE ID=%?1%")
	public Book findBookById(Long id) throws SQLException {
		String getBookFormat = """
				SELECT *
				FROM
				    Books
				WHERE
				    ID = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBookFormat);) {
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Book book = new Book();
			if (rs.next()) {
				book.setId(rs.getInt("ID"));
				book.setIsbn(rs.getString("ISBN"));
				book.setName(rs.getString("NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setGenre(rs.getString("GENRE"));
				book.setBorrower(rs.getString("BORROWER"));
				book.setSerialName(rs.getString("SERIALNAME"));
				book.setDescription(rs.getString("DESCRIPTION"));
			}
			return book;
		}
	}

	@Override
	@Query("SELECT * FROM Books WHERE NAME=%?1%")
	public Book findBookByName(String name) throws SQLException {
		String getBookFormat = """
				SELECT *
				FROM
				    Books
				WHERE
				    NAME = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBookFormat);) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Book book = new Book();
			if (rs.next()) {
				book.setId(rs.getInt("ID"));
				book.setIsbn(rs.getString("ISBN"));
				book.setName(rs.getString("NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setGenre(rs.getString("GENRE"));
				book.setBorrower(rs.getString("BORROWER"));
				book.setSerialName(rs.getString("SERIALNAME"));
				book.setDescription(rs.getString("DESCRIPTION"));
			}
			return book;
		}
	}

	@Override
	@Query("SELECT * FROM Books WHERE ISBN=%?1%")
	public Book findBookByISBN(String isbn) throws SQLException {
		String getBookFormat = """
				SELECT *
				FROM
				    Books
				WHERE
				    ISBN = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBookFormat);) {
			preparedStatement.setString(1, isbn);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Book book = new Book();
			if (rs.next()) {
				book.setId(rs.getInt("ID"));
				book.setIsbn(rs.getString("ISBN"));
				book.setName(rs.getString("NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setGenre(rs.getString("GENRE"));
				book.setBorrower(rs.getString("BORROWER"));
				book.setSerialName(rs.getString("SERIALNAME"));
				book.setDescription(rs.getString("DESCRIPTION"));
			}
			return book;
		}
	}

	@Override
	@Query("SELECT * FROM Books WHERE SERIALNAME=%?1%")
	public Book findBookBySerialName(String serialName) throws SQLException {
		String getBookFormat = """
				SELECT *
				FROM
				    Books
				WHERE
				    SERIALNAME = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBookFormat);) {
			preparedStatement.setString(1, serialName);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Book book = new Book();
			if (rs.next()) {
				book.setId(rs.getInt("ID"));
				book.setIsbn(rs.getString("ISBN"));
				book.setName(rs.getString("NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setGenre(rs.getString("GENRE"));
				book.setBorrower(rs.getString("BORROWER"));
				book.setSerialName(rs.getString("SERIALNAME"));
				book.setDescription(rs.getString("DESCRIPTION"));
			}
			return book;
		}
	}

	@Override
	@Query("SELECT * FROM Books")
	public List<Book> findAllBooks() throws SQLException {
		String getAuthorFormat = """
				SELECT *
				FROM
				    Books""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getAuthorFormat);) {
			ResultSet rs = preparedStatement.executeQuery();
			List<Book> bookList = new ArrayList<>();
			
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("ID"));
				book.setIsbn(rs.getString("ISBN"));
				book.setName(rs.getString("NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setGenre(rs.getString("GENRE"));
				book.setBorrower(rs.getString("BORROWER"));
				book.setSerialName(rs.getString("SERIALNAME"));
				book.setDescription(rs.getString("DESCRIPTION"));
				
				bookList.add(book);
			}
			return bookList;
		}
	}

	@Override
	@Query("DELETE FROM Books WHERE ID=%?1%")
	public Integer deleteBookById(Long id) throws SQLException {
		String deleteBookFormat = """
                DELETE FROM Books
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookFormat);) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Books WHERE NAME=%?1%")
	public Integer deleteBookByName(String name) throws SQLException {
		String deleteBookFormat = """
                DELETE FROM Books
                WHERE
                    NAME = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookFormat);) {
			preparedStatement.setString(1, name);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Books WHERE ISBN=%?1%")
	public Integer deleteBookByISBN(String isbn) throws SQLException {
		String deleteBookFormat = """
                DELETE FROM Books
                WHERE
                    ISBN = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookFormat);) {
			preparedStatement.setString(1, isbn);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Books WHERE SERIALNAME=%?1%")
	public Integer deleteBookBySerialName(String serialName) throws SQLException {
		String deleteBookFormat = """
                DELETE FROM Books
                WHERE
                    SERIALNAME = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookFormat);) {
			preparedStatement.setString(1, serialName);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Books WHERE ID=%?1%")
	public Integer deleteBook(Book book) throws SQLException {
		String deleteBookFormat = """
                DELETE FROM Books
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookFormat);) {
			preparedStatement.setLong(1, book.getId());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("UPDATE Books SET isbn=%?2%, name=%?3%, author=%?4%, genre=%?5%, borrower=%?6%, serialName=%?7%, description=%?8% WHERE id=%?1%")
	public Integer updateBook(Book book) throws SQLException {
		String updateBookFormat = """
				UPDATE Books
				SET
				    ISBN = ?,
				    NAME = ?,
				    AUTHOR = ?,
				    GENRE = ?,
				    BORROWER = ?,
				    SERIALNAME = ?,
				    DESCRIPTION = ?
				WHERE
				    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookFormat);) {
			preparedStatement.setString(1, book.getIsbn());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getGenre());
			preparedStatement.setString(5, book.getBorrower());
			preparedStatement.setString(6, book.getSerialName());
			preparedStatement.setString(7, book.getDescription());
			preparedStatement.setLong(8, book.getId());
			return preparedStatement.executeUpdate();
		}
	}
}
