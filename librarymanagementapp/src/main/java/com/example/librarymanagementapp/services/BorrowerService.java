package com.example.librarymanagementapp.services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementapp.entity.Borrower;
import com.example.librarymanagementapp.repository.BorrowerRepository;

/**
 * @author Jerry Chen
 * 
 */
@Service
public class BorrowerService {
	private final BorrowerRepository borrowerRepository;
    Logger logger = LoggerFactory.getLogger(BorrowerService.class);
    
    @Autowired
	public BorrowerService(BorrowerRepository borrowerRepository) {
		this.borrowerRepository = borrowerRepository;
	}

    public List<Borrower> getAllBorrowers(String borrowerName) throws SQLException {
        try {
            return borrowerRepository.findAllBorrowers();
        } catch (Exception ex) {
            logger.error("BorrowerService Error: findAllBorrowers " + ex);
            throw ex;
        }
    }
	
	public Borrower findById(Long id) throws SQLException {
        try {
            return borrowerRepository.findBorrowerById(id);
        } catch (Exception ex) {
            logger.error("BorrowerService Error: findById " + ex);
            throw ex;
        }
    }

    public Borrower addBorrower(Borrower borrower) throws SQLException {
        try {
        	borrowerRepository.createBorrower(borrower);
            return borrower;
        } catch (Exception ex) {
            logger.error("BorrowerService Error: addBorrower " + ex);
            throw ex;
        }
    }

    public Borrower updateBorrower(Long id, Borrower borrower) throws SQLException {
        try {
            Borrower borrowerDetails = borrowerRepository.findBorrowerById(id);
            if (borrowerDetails != null) {
                borrowerDetails.setName(borrower.getName());
                borrowerDetails.setEmail(borrower.getEmail());
                borrowerDetails.setPhoneNum(borrower.getPhoneNum());
                borrowerDetails.setAddress(borrower.getAddress());
                borrowerRepository.updateBorrower(borrowerDetails);
                return borrowerDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("borrowerService Error: updateborrower " + ex);
            throw ex;
        }
    }

    public Borrower deleteBorrower(Long id) throws SQLException {
        try {
            Borrower borrowerDetails = borrowerRepository.findBorrowerById(id);
            if (borrowerDetails != null) {
                borrowerRepository.deleteBorrowerById(id);
                return borrowerDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("BorrowerService Error: deleteBorrower " + ex);
            throw ex;
        }
    }
}
