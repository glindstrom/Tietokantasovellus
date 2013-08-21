
package fi.cs.helsinki.glindstr.dao;

/**
 * An interface for database operations.
 * The BaseDao interface provides a method for inserting records to the database.
 * The BaseDao interface provides a method for deleting records from the database.
 * @author Gabriel
 */
public interface BaseDao<T>
{
    /**
     * Inserts a record to the database.
     * @param type the record to be inserted
     */
    void save(T type);
    
    /**
     * Deleted a record from the database.
     * @param id the record to be deleted
     */
    void delete(int id);
    
    
}
