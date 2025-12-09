package builder;

import model.Customer;
import model.Account;
import model.Loan;
import model.Transaction;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Demonstrate Customer Builder
        Customer customer = new CustomerBuilder()
            .customerId(1)
            .fullName("Ahmed Mohamed")
            .email("ahmed@example.com")
            .phone("01234567890")
            .password("password123")
            .build();
        
        System.out.println("Customer created:");
        System.out.println("ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getFullName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println();
        
        // Demonstrate Account Builder
        Account account = new AccountBuilder()
            .accountId(101)
            .customerId(1)
            .accountType("Savings")
            .balance(5000.0)
            .build();
        
        System.out.println("Account created:");
        System.out.println("ID: " + account.getAccountId());
        System.out.println("Customer ID: " + account.getCustomerId());
        System.out.println("Type: " + account.getAccountType());
        System.out.println("Balance: " + account.getBalance());
        System.out.println();
        
        // Demonstrate Loan Builder
        Loan loan = new LoanBuilder()
            .loanId(201)
            .customerId(1)
            .loanType("Personal")
            .amount(10000.0)
            .status("Approved")
            .build();
        
        System.out.println("Loan created:");
        System.out.println("ID: " + loan.getLoanId());
        System.out.println("Customer ID: " + loan.getCustomerId());
        System.out.println("Type: " + loan.getLoanType());
        System.out.println("Amount: " + loan.getAmount());
        System.out.println("Status: " + loan.getStatus());
        System.out.println();
        
        // Demonstrate using Director
        BankEntityDirector director = new BankEntityDirector();
        Customer premiumCustomer = director.createPremiumCustomer();
        
        System.out.println("Premium Customer created by Director:");
        System.out.println("ID: " + premiumCustomer.getCustomerId());
        System.out.println("Name: " + premiumCustomer.getFullName());
        System.out.println("Email: " + premiumCustomer.getEmail());
        System.out.println("Phone: " + premiumCustomer.getPhone());
    }
}