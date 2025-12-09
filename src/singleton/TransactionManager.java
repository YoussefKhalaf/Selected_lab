package singleton;

import service.AccountService;
import service.TransactionService;
import model.Account;
import model.Transaction;
import builder.TransactionBuilder;
import java.util.Date;

public class TransactionManager {
    
    private static TransactionManager instance;
    private AccountService accountService;
    private TransactionService transactionService;
    
    private TransactionManager() {
        accountService = new AccountService();
        transactionService = new TransactionService();
    }
    
    public static TransactionManager getInstance() {
        if (instance == null) {
            synchronized (TransactionManager.class) {
                if (instance == null) {
                    instance = new TransactionManager();
                }
            }
        }
        return instance;
    }
    
    public boolean deposit(int accountId, double amount) {
        if (amount <= 0) {
            return false;
        }
        
        Account account = accountService.get(accountId);
        if (account == null) {
            return false;
        }
        
        double newBalance = account.getBalance() + amount;
        if (accountService.updateBalance(accountId, newBalance)) {
            Transaction transaction = new TransactionBuilder()
                .accountId(accountId)
                .transactionType("Deposit")
                .amount(amount)
                .transactionDate(new Date())
                .build();
            transactionService.save(transaction);
            return true;
        }
        return false;
    }
    
    public boolean withdraw(int accountId, double amount) {
        if (amount <= 0) {
            return false;
        }
        
        Account account = accountService.get(accountId);
        if (account == null) {
            return false;
        }
        
        if (account.getBalance() < amount) {
            return false; // Insufficient balance
        }
        
        double newBalance = account.getBalance() - amount;
        if (accountService.updateBalance(accountId, newBalance)) {
            Transaction transaction = new TransactionBuilder()
                .accountId(accountId)
                .transactionType("Withdraw")
                .amount(amount)
                .transactionDate(new Date())
                .build();
            transactionService.save(transaction);
            return true;
        }
        return false;
    }
    
    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0 || fromAccountId == toAccountId) {
            return false;
        }
        
        Account fromAccount = accountService.get(fromAccountId);
        Account toAccount = accountService.get(toAccountId);
        
        if (fromAccount == null || toAccount == null) {
            return false;
        }
        
        if (fromAccount.getBalance() < amount) {
            return false; // Insufficient balance
        }
        
        // Withdraw from source account
        double fromNewBalance = fromAccount.getBalance() - amount;
        // Deposit to destination account
        double toNewBalance = toAccount.getBalance() + amount;
        
        if (accountService.updateBalance(fromAccountId, fromNewBalance) &&
            accountService.updateBalance(toAccountId, toNewBalance)) {
            
            // Create transaction records
            Transaction fromTransaction = new TransactionBuilder()
                .accountId(fromAccountId)
                .transactionType("Transfer Out")
                .amount(amount)
                .transactionDate(new Date())
                .build();
            transactionService.save(fromTransaction);
            
            Transaction toTransaction = new TransactionBuilder()
                .accountId(toAccountId)
                .transactionType("Transfer In")
                .amount(amount)
                .transactionDate(new Date())
                .build();
            transactionService.save(toTransaction);
            
            return true;
        }
        return false;
    }
}
