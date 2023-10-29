package com.example.librarymanagementapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementapp.daos.LibraryBranchDao;
import com.example.librarymanagementapp.entity.LibraryBranch;
import com.example.librarymanagementapp.entity.LibraryBranch;
import com.example.librarymanagementapp.utils.DatabaseConnection;

/**
 * @author Jerry Chen
 * 
 */
@Repository
public class LibraryBranchRepository implements LibraryBranchDao{
	private final Connection connection;
	
	@Autowired
	public LibraryBranchRepository(DatabaseConnection connection) {
		this.connection = connection.getConnection();
	}
	
	/*@Query("SELECT * FROM Genres WHERE name LIKE %?1%")
	List<LibraryBranch> search(String genre);

	@Query("SELECT * FROM Genres")
	List<LibraryBranch> findAll();

	@Query("SELECT * FROM LibraryBranches WHERE id=%?1%")
	Optional<LibraryBranch> findById(Long id);

	@Query("DELETE FROM LibraryBranches WHERE id=%?1%")
	void deleteById(Long id);

	@Query("INSERT INTO LibraryBranches (name, description) VALUES (%?1%,%?2%)")
	LibraryBranch addLibraryBranch(LibraryBranch book);

	@Query("UPDATE LibraryBranches SET name=%?2%, description=%?3% WHERE id=%?1%")
	LibraryBranch updateLibraryBranch(LibraryBranch bookDetails);*/

	@Override
	@Query("INSERT INTO LibraryBranchs (NAME, DESCRIPTION) VALUES (%?1%,%?2%,%?3%)")
	public Integer createLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
		String insertLibraryBranchFormat = """
				INSERT INTO LibraryBranchs (
				    NAME,
				    DESCRIPTION)
				VALUES (
				    ?, ?
				    )""";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertLibraryBranchFormat);) {
			preparedStatement.setString(1, libraryBranch.getName());
			preparedStatement.setString(2, libraryBranch.getLocation());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("SELECT * FROM LibraryBranchs WHERE ID=%?1%")
	public LibraryBranch findLibraryBranchById(Long id) throws SQLException {
		String getLibraryBranchFormat = """
				SELECT *
				FROM
				    LibraryBranchs
				WHERE
				    ID = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getLibraryBranchFormat);) {
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			LibraryBranch libraryBranch = new LibraryBranch();
			if (rs.next()) {
				libraryBranch.setId(rs.getLong("ID"));
				libraryBranch.setName(rs.getString("NAME"));
				libraryBranch.setLocation(rs.getString("DESCRIPTION"));
			}
			return libraryBranch;
		}
	}

	@Override
	@Query("SELECT * FROM LibraryBranchs WHERE NAME=%?1%")
	public LibraryBranch findLibraryBranchByName(String name) throws SQLException {
		String getLibraryBranchFormat = """
				SELECT *
				FROM
				    LibraryBranchs
				WHERE
				    NAME = ?""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getLibraryBranchFormat);) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.isBeforeFirst()) {
				return null;
			}

			LibraryBranch libraryBranch = new LibraryBranch();
			if (rs.next()) {
				libraryBranch.setId(rs.getLong("ID"));
				libraryBranch.setName(rs.getString("NAME"));
				libraryBranch.setLocation(rs.getString("DESCRIPTION"));
			}
			return libraryBranch;
		}
	}

	@Override
	@Query("SELECT * FROM LibraryBranchs")
	public List<LibraryBranch> findAllLibraryBranchs() throws SQLException {
		String getLibraryBranchFormat = """
				SELECT *
				FROM
				    LibraryBranchs""";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getLibraryBranchFormat);) {
			ResultSet rs = preparedStatement.executeQuery();
			List<LibraryBranch> libraryBranchList = new ArrayList<>();
			
			while (rs.next()) {
				LibraryBranch libraryBranch = new LibraryBranch();
				libraryBranch.setId(rs.getLong("ID"));
				libraryBranch.setName(rs.getString("NAME"));
				libraryBranch.setLocation(rs.getString("DESCRIPTION"));
				
				libraryBranchList.add(libraryBranch);
			}
			return libraryBranchList;
		}
	}

	@Override
	@Query("DELETE FROM LibraryBranchs WHERE ID=%?1%")
	public Integer deleteLibraryBranchById(Long id) throws SQLException {
		String deleteLibraryBranchFormat = """
                DELETE FROM LibraryBranchs
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteLibraryBranchFormat);) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM LibraryBranchs WHERE NAME=%?1%")
	public Integer deleteLibraryBranchByName(String name) throws SQLException {
		String deleteLibraryBranchFormat = """
                DELETE FROM LibraryBranchs
                WHERE
                    NAME = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteLibraryBranchFormat);) {
			preparedStatement.setString(1, name);
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("DELETE FROM LibraryBranchs WHERE ID=%?1%")
	public Integer deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
		String deleteLibraryBranchFormat = """
                DELETE FROM LibraryBranchs
                WHERE
                    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteLibraryBranchFormat);) {
			preparedStatement.setLong(1, libraryBranch.getId());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	@Query("UPDATE LibraryBranchs SET name=%?2%, description=%?3% WHERE id=%?1%")
	public Integer updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
		String updateLibraryBranchFormat = """
				UPDATE LibraryBranch
				SET
				    NAME = ?,
				    DESCRIPTION = ?
				WHERE
				    ID = ?""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateLibraryBranchFormat);) {
			preparedStatement.setString(1, libraryBranch.getName());
			preparedStatement.setString(2, libraryBranch.getLocation());
			preparedStatement.setLong(3, libraryBranch.getId());
			return preparedStatement.executeUpdate();
		}
	}
}
