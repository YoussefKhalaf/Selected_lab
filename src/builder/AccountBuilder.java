package builder;

import model.Account;

public class AccountBuilder {
    private int accountId;
    private int customerId;
    private String accountType;
    private double balance;
    
    public AccountBuilder() {
        // Default constructor
    }
    
    public AccountBuilder accountId(int accountId) {
        this.accountId = accountId;
        return this;
    }
    
    public AccountBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public AccountBuilder accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }
    
    public AccountBuilder balance(double balance) {
        this.balance = balance;
        return this;
    }
    
    public Account build() {
        Account account = new Account(0, "", 0.0); // Default constructor values
        account.setAccountId(this.accountId);
        account.setCustomerId(this.customerId);
        account.setAccountType(this.accountType);
        account.setBalance(this.balance);
        return account;
    }
}