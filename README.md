# Banking System - Design Patterns Implementation

This project demonstrates the implementation of two important design patterns in a Java Swing banking application:

## Adapter Design Pattern

### Purpose
The Adapter pattern allows incompatible interfaces to work together without modifying existing code. It acts as a bridge between two incompatible interfaces.

### Implementation
1. **DatabaseRepositoryAdapter** - Standardizes repository operations across different entity types
   - `CustomerRepositoryAdapter` - Adapts CustomerRepo to the standard interface
   - `AccountRepositoryAdapter` - Adapts AccountRepo to the standard interface

2. **ExternalPaymentServiceAdapter** - Integrates external payment services with our internal system
   - Converts our internal method calls to the external service's interface
   - Handles data transformation between our domain and the external service

### Benefits
- Enables consistent interface across different repository implementations
- Allows integration with external services without changing core business logic
- Promotes loose coupling between components
- Makes the system more extensible and maintainable

## Proxy Design Pattern

### Purpose
The Proxy pattern provides a surrogate or placeholder for another object to control access to it. It can add functionality like logging, security, or lazy initialization.

### Implementation
1. **Service Proxies** - Add cross-cutting concerns to service classes
   - `CustomerServiceProxy` - Adds logging and performance monitoring to CustomerService
   - `AccountServiceProxy` - Adds logging and performance monitoring to AccountService
   - `LoanServiceProxy` - Adds logging and performance monitoring to LoanService
   - `TransactionServiceProxy` - Adds logging and performance monitoring to TransactionService

2. **DatabaseConnectionProxy** - Manages database connections with logging and connection pooling simulation

### Benefits
- Centralizes cross-cutting concerns like logging and security
- Improves performance through caching and lazy initialization
- Provides transparent access control
- Enhances maintainability by separating concerns

## Integration
Both patterns have been fully integrated throughout the application:
- UI forms now use service proxies instead of direct service instances
- Repository adapters provide consistent data access interfaces
- External payment service is seamlessly integrated through the adapter

## Files Structure
```
src/
├── adapter/
│   ├── DatabaseRepositoryAdapter.java
│   ├── CustomerRepositoryAdapter.java
│   ├── AccountRepositoryAdapter.java
│   └── ExternalPaymentServiceAdapter.java
├── proxy/
│   ├── ServiceProxy.java
│   ├── CustomerServiceProxy.java
│   ├── AccountServiceProxy.java
│   ├── LoanServiceProxy.java
│   ├── TransactionServiceProxy.java
│   └── DatabaseConnectionProxy.java
└── ui/
    ├── CustomerListForm.java
    ├── AccountListForm.java
    ├── LoanListForm.java
    ├── TransactionForm.java
    ├── TransactionListForm.java
    └── DashboardForm.java
```

## Usage
The application maintains full backward compatibility while providing enhanced functionality through the design patterns. All existing features work as before, with added benefits of logging, performance monitoring, and improved extensibility.