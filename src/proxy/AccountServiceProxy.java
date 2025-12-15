package proxy;

import service.AccountService;
import model.Account;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Proxy class for AccountService
 * This proxy adds logging, performance monitoring, and access control to the AccountService
 */
public class AccountServiceProxy extends AccountService {
    
    private static final Logger logger = Logger.getLogger(AccountServiceProxy.class.getName());
    
    private AccountService accountService;
    
    public AccountServiceProxy() {
        this.accountService = new AccountService();
    }
    
    public AccountServiceProxy(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @Override
    public List<Account> getByCustomer(int customerId) {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching accounts for customer: " + customerId);
        
        try {
            List<Account> result = accountService.getByCustomer(customerId);
            long endTime = System.currentTimeMillis();
            logger.info("Successfully fetched " + result.size() + " accounts for customer " + customerId + " in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching accounts for customer " + customerId + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean create(Account a) {
        long startTime = System.currentTimeMillis();
        logger.info("Creating new account for customer: " + a.getCustomerId());
        
        try {
            boolean result = accountService.create(a);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully created account for customer " + a.getCustomerId() + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to create account for customer " + a.getCustomerId());
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating account for customer " + a.getCustomerId() + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public Account get(int id) {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching account: " + id);
        
        try {
            Account result = accountService.get(id);
            long endTime = System.currentTimeMillis();
            
            if (result != null) {
                logger.info("Successfully fetched account " + id + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Account not found: " + id);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching account " + id + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean updateBalance(int id, double balance) {
        long startTime = System.currentTimeMillis();
        logger.info("Updating balance for account: " + id + " to " + balance);
        
        try {
            boolean result = accountService.updateBalance(id, balance);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully updated balance for account " + id + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to update balance for account " + id);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating balance for account " + id + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public List<Account> getAll() {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching all accounts");
        
        try {
            List<Account> result = accountService.getAll();
            long endTime = System.currentTimeMillis();
            logger.info("Successfully fetched " + result.size() + " accounts in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching accounts: " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public double getTotalBalance() {
        long startTime = System.currentTimeMillis();
        logger.info("Calculating total balance");
        
        try {
            double result = accountService.getTotalBalance();
            long endTime = System.currentTimeMillis();
            logger.info("Successfully calculated total balance: " + result + " in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calculating total balance: " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public Map<String, Object> getAccountWithCustomerName(int accountId) {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching account with customer name: " + accountId);
        
        try {
            Map<String, Object> result = accountService.getAccountWithCustomerName(accountId);
            long endTime = System.currentTimeMillis();
            
            if (result != null) {
                logger.info("Successfully fetched account with customer name " + accountId + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Account with customer name not found: " + accountId);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching account with customer name " + accountId + ": " + e.getMessage(), e);
            throw e;
        }
    }
}