package com.example.librarymanagementapp.services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.librarymanagementapp.entity.LibraryBranch;
import com.example.librarymanagementapp.repository.LibraryBranchRepository;

/**
 * @author Jerry Chen
 * 
 */
public class LibraryBranchService {
	private final LibraryBranchRepository libraryBranchRepository;
	Logger logger = LoggerFactory.getLogger(LibraryBranchService.class);
	
	@Autowired
	public LibraryBranchService(LibraryBranchRepository libraryBranchRepository) {
		this.libraryBranchRepository = libraryBranchRepository;
	}

	public List<LibraryBranch> getAllLibraryBranchs(String libraryBranch) throws SQLException
    {
        try {
            return libraryBranchRepository.findAllLibraryBranchs();
        } catch (Exception ex) {
            logger.error("LibraryBranchService Error: getAllLibraryBranchs " + ex);
            throw ex;
        }
    }

    public LibraryBranch findById(Long id) throws SQLException {
        try {
            return libraryBranchRepository.findLibraryBranchById(id);
        } catch (Exception ex) {
            logger.error("LibraryBranchService Error: findById " + ex);
            throw ex;
        }
    }

    public LibraryBranch addLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
        try {
        	libraryBranchRepository.createLibraryBranch(libraryBranch);
            return libraryBranch;
        } catch (Exception ex) {
            logger.error("LibraryBranchService Error: addLibraryBranch " + ex);
            throw ex;
        }
    }

    public LibraryBranch updateLibraryBranch(Long id, LibraryBranch libraryBranch) throws SQLException {
        try {
            LibraryBranch libraryBranchDetails = libraryBranchRepository.findLibraryBranchById(id);
            if (libraryBranchDetails != null) {
                libraryBranchDetails.setName(libraryBranch.getName());
                libraryBranchDetails.setLocation(libraryBranch.getLocation());
                libraryBranchRepository.updateLibraryBranch(libraryBranchDetails);
                return libraryBranchDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("LibraryBranchService Error: updateLibraryBranch " + ex);
            throw ex;
        }
    }

    public LibraryBranch deleteLibraryBranch(Long id) throws SQLException {
        try {
            LibraryBranch libraryBranchDetails = libraryBranchRepository.findLibraryBranchById(id);
            if (libraryBranchDetails != null) {
                libraryBranchRepository.deleteLibraryBranchById(id);
                return libraryBranchDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("LibraryBranchService Error: deleteLibraryBranch " + ex);
            throw ex;
        }
    }
}
