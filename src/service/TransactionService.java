package service;

import model.Transaction;
import repo.TransactionRepo;
import java.util.List;

public class TransactionService {

    private TransactionRepo repo = new TransactionRepo();

    public boolean save(Transaction t) {
        return repo.insert(t);
    }
    
    public List<Transaction> getByAccount(int accountId) {
        return repo.getByAccount(accountId);
    }
    
    public List<Transaction> getAll() {
        return repo.getAll();
    }
}
