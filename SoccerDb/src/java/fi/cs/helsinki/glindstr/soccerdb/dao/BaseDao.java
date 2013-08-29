
package fi.cs.helsinki.glindstr.soccerdb.dao;

/**
 * An interface for database operations.
 * The BaseDao interface provides a method for inserting records to the database.
 * The BaseDao interface provides a method for deleting records from the database.
 * 
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
    
    /**
     * Checks whether a record already exists in the database
     * @param type the record to be checked
     * @return true if the record exists in the database, false otherwise
     */
    boolean recordExists(T type);
    
    
}
