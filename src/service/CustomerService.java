package service;

import repo.CustomerRepo;
import repo.AccountRepo;
import repo.TransactionRepo;
import model.Customer;
import java.util.List;
// Import the proxy
import proxy.CustomerServiceProxy;

public class CustomerService {

    private CustomerRepo repo = new CustomerRepo();
    private AccountRepo accountRepo = new AccountRepo();
    private TransactionRepo transactionRepo = new TransactionRepo();

    public List<Customer> getAll() {
        return repo.getAll();
    }

    public boolean add(Customer c) {
        return repo.insert(c);
    }

    public boolean update(Customer c) {
        return repo.update(c);
    }

    public boolean delete(int id) {
        // The repo now handles cascade deletion properly
        return repo.delete(id);
    }
    
    // Check if customer can be deleted (has no transactions)
    public boolean canDeleteCustomer(int customerId) {
        List<model.Account> accounts = accountRepo.getAllByCustomer(customerId);
        
        // Check if any account has transactions
        for (model.Account account : accounts) {
            List<model.Transaction> transactions = transactionRepo.getByAccount(account.getAccountId());
            if (!transactions.isEmpty()) {
                return false; // Found transactions, cannot delete
            }
        }
        
        return true; // No transactions found, can delete
    }
    
    // Get customer account count
    public int getCustomerAccountCount(int customerId) {
        return repo.getAccountCount(customerId);
    }
    
    // New method to find customer by ID
    public Customer getById(int id) {
        return repo.findById(id);
    }
    
    // New method to get customer name by ID
    public String getCustomerName(int customerId) {
        Customer customer = repo.findById(customerId);
        return customer != null ? customer.getFullName() : "Unknown";
    }
}