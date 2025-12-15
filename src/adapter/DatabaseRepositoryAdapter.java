package adapter;

import java.util.List;

/**
 * Adapter interface to standardize database repository operations
 * This adapter pattern allows different repository implementations to work with a unified interface
 */
public interface DatabaseRepositoryAdapter<T> {
    
    /**
     * Find all entities
     * @return List of all entities
     */
    List<T> findAll();
    
    /**
     * Find entity by ID
     * @param id The entity ID
     * @return The entity or null if not found
     */
    T findById(int id);
    
    /**
     * Save an entity
     * @param entity The entity to save
     * @return true if successful, false otherwise
     */
    boolean save(T entity);
    
    /**
     * Update an entity
     * @param entity The entity to update
     * @return true if successful, false otherwise
     */
    boolean update(T entity);
    
    /**
     * Delete an entity by ID
     * @param id The entity ID to delete
     * @return true if successful, false otherwise
     */
    boolean delete(int id);
}