package adapter;

import repo.AccountRepo;
import model.Account;
import java.util.List;

/**
 * Adapter class that implements the DatabaseRepositoryAdapter interface for Account entities
 * This adapter wraps the existing AccountRepo and provides a standardized interface
 */
public class AccountRepositoryAdapter implements DatabaseRepositoryAdapter<Account> {
    
    private AccountRepo accountRepo;
    
    public AccountRepositoryAdapter() {
        this.accountRepo = new AccountRepo();
    }
    
    public AccountRepositoryAdapter(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
    
    @Override
    public List<Account> findAll() {
        return accountRepo.getAll();
    }
    
    @Override
    public Account findById(int id) {
        return accountRepo.findById(id);
    }
    
    @Override
    public boolean save(Account account) {
        return accountRepo.insert(account);
    }
    
    @Override
    public boolean update(Account account) {
        return accountRepo.update(account);
    }
    
    @Override
    public boolean delete(int id) {
        return accountRepo.delete(id);
    }
    
    // Delegate additional methods from AccountRepo
    public List<Account> getAllByCustomer(int customerId) {
        return accountRepo.getAllByCustomer(customerId);
    }
    
    public boolean updateBalance(int accountId, double newBalance) {
        return accountRepo.updateBalance(accountId, newBalance);
    }
    
    public java.util.Map<String, Object> getAccountWithCustomerName(int accountId) {
        return accountRepo.getAccountWithCustomerName(accountId);
    }
}