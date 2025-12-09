package builder;

import model.Loan;

public class LoanBuilder {
    private int loanId;
    private int customerId;
    private String loanType;
    private double amount;
    private String status;
    
    public LoanBuilder() {
        // Default constructor
    }
    
    public LoanBuilder loanId(int loanId) {
        this.loanId = loanId;
        return this;
    }
    
    public LoanBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public LoanBuilder loanType(String loanType) {
        this.loanType = loanType;
        return this;
    }
    
    public LoanBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }
    
    public LoanBuilder status(String status) {
        this.status = status;
        return this;
    }
    
    public Loan build() {
        Loan loan = new Loan();
        loan.setLoanId(this.loanId);
        loan.setCustomerId(this.customerId);
        loan.setLoanType(this.loanType);
        loan.setAmount(this.amount);
        loan.setStatus(this.status);
        return loan;
    }
}