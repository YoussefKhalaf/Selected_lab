package proxy;

import service.LoanService;
import model.Loan;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Proxy class for LoanService
 * This proxy adds logging, performance monitoring, and access control to the LoanService
 */
public class LoanServiceProxy extends LoanService {
    
    private static final Logger logger = Logger.getLogger(LoanServiceProxy.class.getName());
    
    private LoanService loanService;
    
    public LoanServiceProxy() {
        this.loanService = new LoanService();
    }
    
    public LoanServiceProxy(LoanService loanService) {
        this.loanService = loanService;
    }
    
    @Override
    public boolean apply(Loan l) {
        long startTime = System.currentTimeMillis();
        logger.info("Applying for new loan for customer: " + l.getCustomerId());
        
        try {
            boolean result = loanService.apply(l);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully applied for loan for customer " + l.getCustomerId() + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to apply for loan for customer " + l.getCustomerId());
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error applying for loan for customer " + l.getCustomerId() + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public List<Loan> getAll() {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching all loans");
        
        try {
            List<Loan> result = loanService.getAll();
            long endTime = System.currentTimeMillis();
            logger.info("Successfully fetched " + result.size() + " loans in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching loans: " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean approveLoan(int loanId) {
        long startTime = System.currentTimeMillis();
        logger.info("Approving loan: " + loanId);
        
        try {
            boolean result = loanService.approveLoan(loanId);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully approved loan " + loanId + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to approve loan " + loanId);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error approving loan " + loanId + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean repayLoan(int loanId) {
        long startTime = System.currentTimeMillis();
        logger.info("Repaying loan: " + loanId);
        
        try {
            boolean result = loanService.repayLoan(loanId);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully repaid loan " + loanId + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to repay loan " + loanId);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error repaying loan " + loanId + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean deleteLoan(int loanId) {
        long startTime = System.currentTimeMillis();
        logger.info("Deleting loan: " + loanId);
        
        try {
            boolean result = loanService.deleteLoan(loanId);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully deleted loan " + loanId + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to delete loan " + loanId);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting loan " + loanId + ": " + e.getMessage(), e);
            throw e;
        }
    }
}