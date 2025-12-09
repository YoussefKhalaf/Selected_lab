package factory;

import model.Account;
import model.CheckingAccount;
import model.LoanAccount;
import model.SavingAccount;

public class AccountFactory {

    public static Account createAccount(int customerId, String type, double amount) {
        switch (type.toLowerCase()) {
            case "savings":
                return new SavingAccount(customerId, amount);
            case "checking":
                return new CheckingAccount(customerId, amount);
            case "loan":
                return new LoanAccount(customerId, amount);
            default:
                throw new IllegalArgumentException("Unknown account type");
        }
    }
}
