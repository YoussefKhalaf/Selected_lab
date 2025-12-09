package model;

public class LoanAccount extends Account {

    public LoanAccount(int customerId, double balance) {
        super(customerId, "LOAN", balance);
    }
}
