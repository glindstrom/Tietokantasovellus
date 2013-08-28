

package fi.cs.helsinki.glindstr.soccerdb.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class provides a connection to the specified database.
 * 
 */
public class ConnectionProvider 
{
//    /**
//     * database driver
//     */
//    private static final String dbDriver = "org.apache.derby.jdbc.ClientDriver";
//    /**
//     * location of the database
//     */
//    private static final String dbServer = "jdbc:derby://localhost:1527/soccerdb";
//    /**
//     * username for the database
//     */
//    private static final String dbUser = "s";
//    /**
//     * password for the database
//     */
//    private static final String dbPassword = "s";
    
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
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }        
        
        return conn;
    }
    
}
