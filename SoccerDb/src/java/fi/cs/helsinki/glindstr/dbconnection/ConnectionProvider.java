

package fi.cs.helsinki.glindstr.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class handles communication with the database.
 * 
 */
public class ConnectionProvider 
{
    /**
     * database driver
     */
    private static final String dbDriver = "org.postgresql.Driver";
    /**
     * location of the database
     */
    private static final String dbServer = "jdbc:postgresql://localhost/glindstr";
    /**
     * username for the database
     */
    private static final String dbUser = "glindstr";
    /**
     * password for the database
     */
    private static final String dbPassword = "6752df0205f487e9";
    
    /**
     * Establishes a connection with the database.
     * @return a connection
     */
    public static Connection createConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName(dbDriver).newInstance();
            conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            System.out.println("Loading the driver failed. " + e.getMessage());
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        return conn;
    }
    
}