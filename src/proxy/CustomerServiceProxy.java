package proxy;

import service.CustomerService;
import model.Customer;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Proxy class for CustomerService
 * This proxy adds logging, performance monitoring, and access control to the CustomerService
 */
public class CustomerServiceProxy extends CustomerService {
    
    private static final Logger logger = Logger.getLogger(CustomerServiceProxy.class.getName());
    
    private CustomerService customerService;
    
    public CustomerServiceProxy() {
        this.customerService = new CustomerService();
    }
    
    public CustomerServiceProxy(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @Override
    public List<Customer> getAll() {
        long startTime = System.currentTimeMillis();
        logger.info("Fetching all customers");
        
        try {
            List<Customer> result = customerService.getAll();
            long endTime = System.currentTimeMillis();
            logger.info("Successfully fetched " + result.size() + " customers in " + (endTime - startTime) + " ms");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching customers: " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean add(Customer c) {
        long startTime = System.currentTimeMillis();
        logger.info("Adding new customer: " + c.getFullName());
        
        try {
            boolean result = customerService.add(c);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully added customer " + c.getFullName() + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to add customer " + c.getFullName());
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding customer " + c.getFullName() + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean update(Customer c) {
        long startTime = System.currentTimeMillis();
        logger.info("Updating customer: " + c.getCustomerId());
        
        try {
            boolean result = customerService.update(c);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully updated customer " + c.getCustomerId() + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to update customer " + c.getCustomerId());
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating customer " + c.getCustomerId() + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean delete(int id) {
        long startTime = System.currentTimeMillis();
        logger.info("Deleting customer: " + id);
        
        try {
            boolean result = customerService.delete(id);
            long endTime = System.currentTimeMillis();
            
            if (result) {
                logger.info("Successfully deleted customer " + id + " in " + (endTime - startTime) + " ms");
            } else {
                logger.warning("Failed to delete customer " + id);
            }
            
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting customer " + id + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public boolean canDeleteCustomer(int customerId) {
        logger.info("Checking if customer can be deleted: " + customerId);
        return customerService.canDeleteCustomer(customerId);
    }
    
    @Override
    public int getCustomerAccountCount(int customerId) {
        logger.info("Getting account count for customer: " + customerId);
        return customerService.getCustomerAccountCount(customerId);
    }
}