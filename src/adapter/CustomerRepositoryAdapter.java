package adapter;

import repo.CustomerRepo;
import model.Customer;
import java.util.List;

/**
 * Adapter class that implements the DatabaseRepositoryAdapter interface for Customer entities
 * This adapter wraps the existing CustomerRepo and provides a standardized interface
 */
public class CustomerRepositoryAdapter implements DatabaseRepositoryAdapter<Customer> {
    
    private CustomerRepo customerRepo;
    
    public CustomerRepositoryAdapter() {
        this.customerRepo = new CustomerRepo();
    }
    
    public CustomerRepositoryAdapter(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    
    @Override
    public List<Customer> findAll() {
        return customerRepo.getAll();
    }
    
    @Override
    public Customer findById(int id) {
        return customerRepo.findById(id);
    }
    
    @Override
    public boolean save(Customer customer) {
        return customerRepo.insert(customer);
    }
    
    @Override
    public boolean update(Customer customer) {
        return customerRepo.update(customer);
    }
    
    @Override
    public boolean delete(int id) {
        return customerRepo.delete(id);
    }
    
    // Delegate additional methods from CustomerRepo
    public Customer findByEmailPassword(String email, String pass) {
        return customerRepo.findByEmailPassword(email, pass);
    }
    
    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }
    
    public boolean hasAccounts(int customerId) {
        return customerRepo.hasAccounts(customerId);
    }
    
    public int getAccountCount(int customerId) {
        return customerRepo.getAccountCount(customerId);
    }
}