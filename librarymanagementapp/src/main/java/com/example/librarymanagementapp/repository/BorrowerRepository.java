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

import com.example.librarymanagementapp.daos.BorrowerDao;
import com.example.librarymanagementapp.entity.Borrower;
import com.example.librarymanagementapp.utils.DatabaseConnection;

/**
 * @author Jerry Chen
 * 
 */
@Repository
public class BorrowerRepository implements BorrowerDao {
	private final Connection connection;
	
	@Autowired
	public BorrowerRepository(DatabaseConnection connection) {
		this.connection = connection.getConnection();
	}
	
	/*@Query("SELECT * FROM Borrowers WHERE name LIKE %?1%")
	List<Borrower> search(String borrower);
	
	@Query("SELECT * FROM Borrowers")
	List<Borrower> findAll();

	@Query("SELECT * FROM Borrowers WHERE id=%?1%")
	Optional<Borrower> findById(Long id);

	@Query("DELETE FROM Borrowers WHERE id=%?1%")
	void deleteById(Long id);

	@Query("INSERT INTO Borrowers (name, address, phone, email) VALUES (%?1%,%?2%,%?3%,%?4%)")
	Borrower addBorrower(Borrower book);

	@Query("UPDATE Borrowers SET name=%?2%, address=%?3%, phone=%?4%, email=%?5% WHERE id=%?1%")
	Borrower updateBorrower(Borrower bookDetails);*/

	@Override
	@Query("INSERT INTO Borrowers (NAME, ADDRESS, PHONENUM, EMAIL) VALUES (%?1%,%?2%,%?3%,%?4%)")
	public Integer createBorrower(Borrower borrower) throws SQLException {
		String insertBorrowerFormat = """
				INSERT INTO Borrowers (
				    NAME,
				    ADDRESS,
				    PHONENUM,
				    EMAIL)
				VALUES (
				    ?, ?, ?, ?
				    )""";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertBorrowerFormat);) {
			preparedStatement.setString(1, borrower.getName());
			preparedStatement.setString(2, borrower.getAddress());
			preparedStatement.setString(3, borrower.getPhoneNum());
			preparedStatement.setString(4, borrower.getEmail());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("SELECT * FROM Borrowers WHERE ID=%?1%")
	public Borrower findBorrowerById(Long id) throws SQLException {
		String getBorrowerFormat = """
				SELECT *
				FROM
				    Borrowers
				WHERE
				    ID = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBorrowerFormat);) {
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Borrower borrower = new Borrower();
			if (rs.next()) {
				borrower.setId(rs.getInt("ID"));
				borrower.setName(rs.getString("NAME"));
				borrower.setAddress(rs.getString("ADDRESS"));
				borrower.setPhoneNum(rs.getString("PHONENUM"));
				borrower.setEmail(rs.getString("EMAIL"));
			}
			return borrower;
		}
	}

	@Override
	@Query("SELECT * FROM Borrowers WHERE NAME=%?1%")
	public Borrower findBorrowerByName(String name) throws SQLException {
		String getBorrowerFormat = """
				SELECT *
				FROM
				    Borrowers
				WHERE
				    NAME = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBorrowerFormat);) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			Borrower borrower = new Borrower();
			if (rs.next()) {
				borrower.setId(rs.getInt("ID"));
				borrower.setName(rs.getString("NAME"));
				borrower.setAddress(rs.getString("ADDRESS"));
				borrower.setPhoneNum(rs.getString("PHONENUM"));
				borrower.setEmail(rs.getString("EMAIL"));
			}
			return borrower;
		}
	}

	@Override
	@Query("SELECT * FROM Borrowers")
	public List<Borrower> findAllBorrowers() throws SQLException {
		String getBorrowerFormat = """
				SELECT *
				FROM
				    Borrowers""";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(getBorrowerFormat);) {
			ResultSet rs = preparedStatement.executeQuery();
			List<Borrower> borrowerList = new ArrayList<>();
			
			while (rs.next()) {
				Borrower borrower = new Borrower();
				borrower.setId(rs.getInt("ID"));
				borrower.setName(rs.getString("NAME"));
				borrower.setAddress(rs.getString("ADDRESS"));
				borrower.setPhoneNum(rs.getString("PHONENUM"));
				borrower.setEmail(rs.getString("EMAIL"));
				
				borrowerList.add(borrower);
			}
			return borrowerList;
		}
	}

	@Override
	@Query("DELETE FROM Borrowers WHERE ID=%?1%")
	public Integer deleteBorrowerById(Long id) throws SQLException {
		String deleteBorrowerFormat = """
                DELETE FROM Borrowers
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBorrowerFormat);) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Borrowers WHERE NAME=%?1%")
	public Integer deleteBorrowerByName(String name) throws SQLException {
		String deleteBorrowerFormat = """
                DELETE FROM Borrowers
                WHERE
                    NAME = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBorrowerFormat);) {
			preparedStatement.setString(1, name);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM Borrowers WHERE ID=%?1%")
	public Integer deleteBorrower(Borrower borrower) throws SQLException {
		String deleteBorrowerFormat = """
                DELETE FROM Borrowers
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBorrowerFormat);) {
			preparedStatement.setLong(1, borrower.getId());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("UPDATE Borrowers SET NAME=%?1%, ADDRESS=%?2%, PHONENUM=%?3%, EMAIL=%?4% WHERE NAME=%?5%")
	public Integer updateBorrower(Borrower borrower) throws SQLException {
		String updateAuthorFormat = """
				UPDATE Author
				SET
				    NAME = ?,
				    ADDRESS = ?,
				    PHONENUM = ?,
				    EMAIL = ? 
				WHERE
				    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateAuthorFormat);) {
			preparedStatement.setString(1, borrower.getName());
			preparedStatement.setString(2, borrower.getAddress());
			preparedStatement.setString(3, borrower.getPhoneNum());
			preparedStatement.setString(4, borrower.getEmail());
			preparedStatement.setLong(5, borrower.getId());
			return preparedStatement.executeUpdate();
		}
	}
}
