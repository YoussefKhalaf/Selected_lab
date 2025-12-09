package model;

public class Loan {

    private int loanId;
    private int customerId;
    private String loanType;
    private double amount;
    private String status;

    public Loan() {
    }

    public Loan(int customerId, String loanType, double amount) {
        this.customerId = customerId;
        this.status = "Pending";
        this.loanType = loanType;
        this.amount = amount;
    }

    public Loan(int loanId, int customerId, String loanType, double amount, String status) {
        this.customerId = customerId;
        this.loanId = loanId;
        this.status = status;
        this.loanType = loanType;
        this.amount = amount;
    }

    // Getters & Setters
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
