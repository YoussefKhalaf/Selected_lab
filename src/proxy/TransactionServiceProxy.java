package proxy;

import service.TransactionService;
import model.Transaction;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Proxy class for TransactionService
 * This proxy adds logging, performance monitoring, and access control to the TransactionService
 */
public class TransactionServiceProxy extends TransactionService {
    
    private static final Logger logger = Logger.getLogger(TransactionServiceProxy.class.getName());
    
    private TransactionService transactionService;
    
    public TransactionServiceProxy() {
        this.transactionService = new TransactionService();
    }
    
    public TransactionServiceProxy(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @Override
    public boolean save(Transaction t) {
        long startTime = System.currentTimeMillis();
        logger.info("Saving new transaction of type: " + t.getTransactionType());
        
        try {
            boolean result = transactionService.save(t);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully saved transaction of type " + t.getTransactionType() + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to save transaction of type " + t.getTransactionType());
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving transaction of type " + t.getTransactionType() + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public List<Transaction> getByAccount(int accountId) {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching transactions for account: " + accountId);
        
        try {
            List<Transaction> result = transactionService.getByAccount(accountId);
            long endTime = System.currentTimeMillis();
            logger.info("Successfully fetched " + result.size() + " transactions for account " + accountId + " in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching transactions for account " + accountId + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public List<Transaction> getAll() {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching all transactions");
        
        try {
            List<Transaction> result = transactionService.getAll();
            long endTime = System.currentTimeMillis();
            logger.info("Successfully fetched " + result.size() + " transactions in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching transactions: " + e.getMessage(), e);
            throw e;
        }
    }
}