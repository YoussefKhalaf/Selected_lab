package model;

public class CarLoan extends Loan {

    public CarLoan(int customerId, double amount) {
        super();
        setCustomerId(customerId);
        setLoanType("CAR");
        setAmount(amount);
        setStatus("Pending");
    }
}
