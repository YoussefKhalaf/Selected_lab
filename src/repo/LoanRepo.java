package repo;

import model.Loan;
import util.DBConnection;
import builder.LoanBuilder;
import java.sql.*;
import java.util.*;

public class LoanRepo {

    public List<Loan> getAll() {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM Loans";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                Loan loan = new LoanBuilder()
                    .loanId(rs.getInt("loan_id"))
                    .customerId(rs.getInt("customer_id"))
                    .loanType(rs.getString("loan_type"))
                    .amount(rs.getDouble("amount"))
                    .status(rs.getString("status"))
                    .build();
                list.add(loan);
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }

    public boolean insert(Loan l) {
        String sql = "INSERT INTO Loans(customer_id, loan_type, amount) VALUES(?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getCustomerId());
            ps.setString(2, l.getLoanType());
            ps.setDouble(3, l.getAmount());

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }
    
    public boolean updateStatus(int loanId, String status) {
        String sql = "UPDATE Loans SET status=? WHERE loan_id=?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, loanId);
            
            return ps.executeUpdate() > 0;
            
        } catch(Exception ex) { ex.printStackTrace(); }
        
        return false;
    }
    
    public boolean delete(int loanId) {
        String sql = "DELETE FROM Loans WHERE loan_id=?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, loanId);
            
            return ps.executeUpdate() > 0;
            
        } catch(Exception ex) { ex.printStackTrace(); }
        
        return false;
    }
}
