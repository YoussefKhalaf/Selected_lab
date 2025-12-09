package factory;

import model.CarLoan;
import model.HomeLoan;
import model.Loan;
import model.PersonalLoan;

public class LoanFactory {

    public static Loan createLoan(String type, int customerId, double amount, double interestRate, double durationMonths) {
        switch (type.toLowerCase()) {
            case "personal":
                return new PersonalLoan(customerId, amount);
            case "home":
                return new HomeLoan(customerId, amount);
            case "car":
                return new CarLoan(customerId, amount);
            default:
                throw new IllegalArgumentException("Unknown loan type");
        }
    }
}
