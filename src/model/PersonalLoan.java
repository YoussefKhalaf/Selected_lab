package model;

public class PersonalLoan extends Loan {

    public PersonalLoan(int customerId, double amount) {
        super();
        setCustomerId(customerId);
        setLoanType("PERSONAL");
        setAmount(amount);
        setStatus("Pending");
    }

}
