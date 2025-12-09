package builder;

import model.Transaction;
import java.util.Date;

public class TransactionBuilder {
    private int transactionId;
    private int accountId;
    private String transactionType;
    private double amount;
    private Date transactionDate;
    
    public TransactionBuilder() {
        // Default constructor
    }
    
    public TransactionBuilder transactionId(int transactionId) {
        this.transactionId = transactionId;
        return this;
    }
    
    public TransactionBuilder accountId(int accountId) {
        this.accountId = accountId;
        return this;
    }
    
    public TransactionBuilder transactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }
    
    public TransactionBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }
    
    public TransactionBuilder transactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }
    
    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(this.transactionId);
        transaction.setAccountId(this.accountId);
        transaction.setTransactionType(this.transactionType);
        transaction.setAmount(this.amount);
        transaction.setTransactionDate(this.transactionDate);
        return transaction;
    }
}