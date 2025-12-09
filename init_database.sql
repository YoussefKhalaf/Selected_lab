-- Database initialization script for Banking System
-- Create the BankSystem database
CREATE DATABASE BankSystem;
GO

USE BankSystem;
GO

-- Create Customers table
CREATE TABLE Customers (
    customer_id INT IDENTITY(1,1) PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    phone NVARCHAR(20),
    password NVARCHAR(100) NOT NULL
);
GO

-- Create Accounts table
CREATE TABLE Accounts (
    account_id INT IDENTITY(1,1) PRIMARY KEY,
    customer_id INT NOT NULL,
    account_type NVARCHAR(20) NOT NULL,
    balance DECIMAL(18,2) DEFAULT 0.00,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE
);
GO

-- Create Loans table
CREATE TABLE Loans (
    loan_id INT IDENTITY(1,1) PRIMARY KEY,
    customer_id INT NOT NULL,
    loan_type NVARCHAR(20) NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    status NVARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE
);
GO

-- Create Transactions table
CREATE TABLE Transactions (
    transaction_id INT IDENTITY(1,1) PRIMARY KEY,
    from_account_id INT,
    to_account_id INT,
    transaction_type NVARCHAR(20) NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    transaction_date DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (from_account_id) REFERENCES Accounts(account_id) ON DELETE CASCADE,
    FOREIGN KEY (to_account_id) REFERENCES Accounts(account_id) ON DELETE CASCADE
);
GO

-- Insert default admin user
INSERT INTO Customers (full_name, email, phone, password) 
VALUES ('Admin User', 'admin@bank.com', '01234567890', 'admin123');
GO