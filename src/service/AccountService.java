package service;

import repo.AccountRepo;
import model.Account;
import java.util.List;
import java.util.Map;
// Import the proxy
import proxy.AccountServiceProxy;

public class AccountService {

    private AccountRepo repo = new AccountRepo();

    public List<Account> getByCustomer(int customerId) {
        return repo.getAllByCustomer(customerId);
    }

    public boolean create(Account a) {
        return repo.insert(a);
    }

    public Account get(int id) {
        return repo.findById(id);
    }

    public boolean updateBalance(int id, double balance) {
        return repo.updateBalance(id, balance);
    }
    
    public List<Account> getAll() {
        return repo.getAll();
    }
    
    public double getTotalBalance() {
        List<Account> accounts = getAll();
        double total = 0;
        for (Account a : accounts) {
            total += a.getBalance();
        }
        return total;
    }
    
    // Get account with customer name
    public Map<String, Object> getAccountWithCustomerName(int accountId) {
        return repo.getAccountWithCustomerName(accountId);
    }
    
    // New methods for full account management
    public boolean update(Account account) {
        return repo.update(account);
    }
    
    public boolean delete(int id) {
        return repo.delete(id);
    }
}