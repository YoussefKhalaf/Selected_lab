package proxy;

import util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Proxy class for database connections
 * This proxy adds connection pooling, logging, and monitoring to database connections
 */
public class DatabaseConnectionProxy {
    
    private static final Logger logger = Logger.getLogger(DatabaseConnectionProxy.class.getName());
    
    // Simple connection pool simulation (in a real application, you would use a proper connection pool)
    private static Connection connection = null;
    private static int connectionCount = 0;
    
    /**
     * Get a database connection through the proxy
     * This method adds logging and connection counting
     * @return Database connection
     * @throws SQLException if there's an error getting the connection
     */
    public static Connection getConnection() throws SQLException {
        long startTime = System.currentTimeMillis();
        logger.info("Requesting database connection");
        
        try {
            if (connection == null || connection.isClosed()) {
                logger.info("Creating new database connection");
                connection = DBConnection.getConnection();
                connectionCount++;
                logger.info("New connection created. Total connections: " + connectionCount);
            }
            
            long endTime = System.currentTimeMillis();
            logger.info("Database connection obtained in " + (endTime - startTime) + " ms");
            
            return connection;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obtaining database connection: " + e.getMessage(), e);
            throw new SQLException("Failed to obtain database connection", e);
        }
    }
    
    /**
     * Close the database connection
     * In a real connection pool, this would return the connection to the pool rather than closing it
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                logger.info("Database connection closed");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing database connection: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get the current connection count
     * @return Number of connections created
     */
    public static int getConnectionCount() {
        return connectionCount;
    }
}