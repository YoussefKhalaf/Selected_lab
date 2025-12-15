package repo;

import model.Account;
import util.DBConnection;
import builder.AccountBuilder;
import java.sql.*;
import java.util.*;

public class AccountRepo {

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                list.add(new AccountBuilder()
                    .accountId(rs.getInt("account_id"))
                    .customerId(rs.getInt("customer_id"))
                    .accountType(rs.getString("account_type"))
                    .balance(rs.getDouble("balance"))
                    .build());
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }

    public List<Account> getAllWithCustomerNames() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT a.*, c.full_name as customer_name FROM Accounts a JOIN Customers c ON a.customer_id = c.customer_id";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                Account account = new AccountBuilder()
                    .accountId(rs.getInt("account_id"))
                    .customerId(rs.getInt("customer_id"))
                    .accountType(rs.getString("account_type"))
                    .balance(rs.getDouble("balance"))
                    .build();
                // We'll store the customer name in a transient field or handle it separately
                list.add(account);
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }

    public List<Account> getAllByCustomer(int customerId) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts WHERE customer_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new AccountBuilder()
                    .accountId(rs.getInt("account_id"))
                    .customerId(rs.getInt("customer_id"))
                    .accountType(rs.getString("account_type"))
                    .balance(rs.getDouble("balance"))
                    .build());
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }

    public boolean insert(Account a) {
        String sql = "INSERT INTO Accounts(customer_id, account_type, balance) VALUES(?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getCustomerId());
            ps.setString(2, a.getAccountType());
            ps.setDouble(3, a.getBalance());

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }

    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE Accounts SET balance=? WHERE account_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }

    public Account findById(int id) {
        String sql = "SELECT * FROM Accounts WHERE account_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new AccountBuilder()
                    .accountId(rs.getInt("account_id"))
                    .customerId(rs.getInt("customer_id"))
                    .accountType(rs.getString("account_type"))
                    .balance(rs.getDouble("balance"))
                    .build();
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return null;
    }
    
    // Get account with customer name
    public Map<String, Object> getAccountWithCustomerName(int accountId) {
        String sql = "SELECT a.*, c.full_name as customer_name FROM Accounts a JOIN Customers c ON a.customer_id = c.customer_id WHERE a.account_id=?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("account", new AccountBuilder()
                    .accountId(rs.getInt("account_id"))
                    .customerId(rs.getInt("customer_id"))
                    .accountType(rs.getString("account_type"))
                    .balance(rs.getDouble("balance"))
                    .build());
                result.put("customerName", rs.getString("customer_name"));
                return result;
            }
            
        } catch(Exception ex) { ex.printStackTrace(); }
        
        return null;
    }
    
    // Add a method to delete an account
    public boolean delete(int id) {
        String sql = "DELETE FROM Accounts WHERE account_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }
    
    // Add a method to update an account (not just balance)
    public boolean update(Account account) {
        String sql = "UPDATE Accounts SET customer_id=?, account_type=?, balance=? WHERE account_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, account.getCustomerId());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());
            ps.setInt(4, account.getAccountId());

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }
}