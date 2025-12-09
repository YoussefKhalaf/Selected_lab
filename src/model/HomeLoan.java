package model;

public class HomeLoan extends Loan {

    public HomeLoan(int customerId, double amount) {
        super();
        setCustomerId(customerId);
        setLoanType("HOME");
        setAmount(amount);
        setStatus("Pending");
    }
}
