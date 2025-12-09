package repo;

import model.Customer;
import util.DBConnection;
import builder.CustomerBuilder;
import java.sql.*;
import java.util.*;

public class CustomerRepo {

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customers";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                list.add(new CustomerBuilder()
                    .customerId(rs.getInt("customer_id"))
                    .fullName(rs.getString("full_name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .password(rs.getString("password"))
                    .build());
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return list;
    }

    public boolean insert(Customer c) {
        String sql = "INSERT INTO Customers(full_name,email,phone,password) VALUES (?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getPassword());

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }

    public boolean update(Customer c) {
        String sql = "UPDATE Customers SET full_name=?,phone=?,email=? WHERE customer_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setInt(4, c.getCustomerId());

            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Customers WHERE customer_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }

    public Customer findByEmailPassword(String email, String pass) {
        String sql = "SELECT * FROM Customers WHERE email=? AND password=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new CustomerBuilder()
                    .customerId(rs.getInt("customer_id"))
                    .fullName(rs.getString("full_name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .password(rs.getString("password"))
                    .build();
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return null;
    }

    public Customer findByEmail(String email) {
        String sql = "SELECT * FROM Customers WHERE email=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new CustomerBuilder()
                    .customerId(rs.getInt("customer_id"))
                    .fullName(rs.getString("full_name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .password(rs.getString("password"))
                    .build();
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return null;
    }
    
    // Check if customer has any accounts
    public boolean hasAccounts(int customerId) {
        String sql = "SELECT COUNT(*) FROM Accounts WHERE customer_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return false;
    }
    
    // Get count of customer's accounts
    public int getAccountCount(int customerId) {
        String sql = "SELECT COUNT(*) FROM Accounts WHERE customer_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception ex) { ex.printStackTrace(); }

        return 0;
    }
}