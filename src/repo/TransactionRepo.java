package repo;

import model.Transaction;
import util.DBConnection;
import builder.TransactionBuilder;
import java.sql.*;
import java.util.*;

public class TransactionRepo {

    public boolean insert(Transaction t) {
        String sql = "INSERT INTO Transactions(account_id, transaction_type, amount) VALUES(?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getAccountId());
            ps.setString(2, t.getTransactionType());
            ps.setDouble(3, t.getAmount());

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }
    
    public List<Transaction> getByAccount(int accountId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE account_id=? ORDER BY transaction_date DESC";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new TransactionBuilder()
                    .transactionId(rs.getInt("transaction_id"))
                    .accountId(rs.getInt("account_id"))
                    .transactionType(rs.getString("transaction_type"))
                    .amount(rs.getDouble("amount"))
                    .transactionDate(rs.getTimestamp("transaction_date"))
                    .build());
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }
    
    public List<Transaction> getAll() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM Transactions ORDER BY transaction_date DESC";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                list.add(new TransactionBuilder()
                    .transactionId(rs.getInt("transaction_id"))
                    .accountId(rs.getInt("account_id"))
                    .transactionType(rs.getString("transaction_type"))
                    .amount(rs.getDouble("amount"))
                    .transactionDate(rs.getTimestamp("transaction_date"))
                    .build());
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }
}
