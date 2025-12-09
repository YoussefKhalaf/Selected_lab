package model;

public class CheckingAccount extends Account {

    public CheckingAccount(int customerId, double balance) {
        super(customerId, "CHECKING", balance);
    }
}
