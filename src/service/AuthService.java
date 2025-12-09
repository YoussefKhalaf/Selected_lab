package service;

import model.Customer;
import repo.CustomerRepo;
import builder.CustomerBuilder;

public class AuthService {

    private static AuthService instance;
    private CustomerRepo repo = new CustomerRepo();
    public Customer loggedUser;
    private static boolean defaultAccountCreated = false;

    private AuthService(){
        createDefaultAccount();
    }

    public static AuthService getInstance() {
        if(instance == null) {
            synchronized (AuthService.class) {
                if(instance == null) instance = new AuthService();
            }
        }
        return instance;
    }

    public boolean login(String email, String password) {
        loggedUser = repo.findByEmailPassword(email, password);
        return loggedUser != null;
    }

    private void createDefaultAccount() {
        if (defaultAccountCreated) return;
        
        try {
            // Check if default account exists
            Customer existing = repo.findByEmail("admin@bank.com");
            if (existing == null) {
                // Create default test account using Builder pattern
                Customer defaultCustomer = new CustomerBuilder()
                    .fullName("Admin User")
                    .email("admin@bank.com")
                    .phone("01234567890")
                    .password("admin123")
                    .build();
                
                if (repo.insert(defaultCustomer)) {
                    defaultAccountCreated = true;
                    System.out.println("Default test account created: admin@bank.com / admin123");
                }
            } else {
                defaultAccountCreated = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
