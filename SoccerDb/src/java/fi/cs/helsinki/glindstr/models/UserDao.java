package fi.cs.helsinki.glindstr.models;

import java.sql.*;
import fi.cs.helsinki.glindstr.dbconnection.ConnectionProvider;

/**
 * This class handles the database access for the user registration function.
 *
 * @author Gabriel
 */
public class UserDao
{
    /**
     * Adds a new user to the database.
     * @param user the user to be added
     */
    public void addUser(User user)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPass());
            ps.executeUpdate();
            conn.close();
        } 
        catch (SQLException e)
        {
            System.out.println(e);
        }        
    }
    
    /**
     * Checks if the user has registered.
     * @param user the user to be validated
     * @return true if the username and password matches the database.
     */
    public boolean validateUser(User user)
    {
        boolean status = false;
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "SELECT * FROM users where username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPass());
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            conn.close();
        } 
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return status;
    }
}
