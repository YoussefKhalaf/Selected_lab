package service;

import model.Loan;
import repo.LoanRepo;
import java.util.List;

public class LoanService {

    private LoanRepo repo = new LoanRepo();

    public boolean apply(Loan l) {
        return repo.insert(l);
    }
    
    public List<Loan> getAll() {
        return repo.getAll();
    }
    
    public boolean approveLoan(int loanId) {
        return repo.updateStatus(loanId, "Approved");
    }
    
    public boolean repayLoan(int loanId) {
        return repo.updateStatus(loanId, "Repaid");
    }
    
    public boolean deleteLoan(int loanId) {
        return repo.delete(loanId);
    }
}
