package builder;

import model.Customer;

public class CustomerBuilder {
    private int customerId;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    
    public CustomerBuilder() {
        // Default constructor
    }
    
    public CustomerBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public CustomerBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    
    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    public CustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    public CustomerBuilder password(String password) {
        this.password = password;
        return this;
    }
    
    public Customer build() {
        Customer customer = new Customer();
        customer.setCustomerId(this.customerId);
        customer.setFullName(this.fullName);
        customer.setEmail(this.email);
        customer.setPhone(this.phone);
        customer.setPassword(this.password);
        return customer;
    }
}