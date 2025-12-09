package builder;

import model.Customer;
import model.Account;
import model.Loan;
import model.Transaction;
import java.util.Date;

public class BankEntityDirector {
    
    public Customer createDefaultCustomer() {
        return new CustomerBuilder()
            .fullName("John Doe")
            .email("john.doe@example.com")
            .phone("123-456-7890")
            .password("securePassword123")
            .build();
    }
    
    public Customer createPremiumCustomer() {
        return new CustomerBuilder()
            .customerId(1001)
            .fullName("Jane Smith")
            .email("jane.smith@premium.com")
            .phone("987-654-3210")
            .password("premiumPass456")
            .build();
    }
    
    public Account createSavingsAccount(int customerId) {
        return new AccountBuilder()
            .customerId(customerId)
            .accountType("Savings")
            .balance(1000.0)
            .build();
    }
    
    public Account createCheckingAccount(int customerId) {
        return new AccountBuilder()
            .customerId(customerId)
            .accountType("Checking")
            .balance(2500.0)
            .build();
    }
    
    public Loan createPersonalLoan(int customerId) {
        return new LoanBuilder()
            .customerId(customerId)
            .loanType("Personal")
            .amount(5000.0)
            .status("Approved")
            .build();
    }
    
    public Transaction createDepositTransaction(int accountId) {
        return new TransactionBuilder()
            .accountId(accountId)
            .transactionType("Deposit")
            .amount(1000.0)
            .transactionDate(new Date())
            .build();
    }
}