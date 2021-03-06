package fi.cs.helsinki.glindstr.soccerdb.dao;

import java.sql.*;
import fi.cs.helsinki.glindstr.soccerdb.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.soccerdb.models.User;

/**
 * This class provides database access for the user registration function.
 *
 * @author Gabriel
 */
public class UserDaoImpl implements UserDao
{

    @Override
    public void save(User user)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPass());
            ps.executeUpdate();
        } 
        catch (SQLException e)
        {
            System.out.println(e);
        } finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                } 
                catch (SQLException ignore)
                {
                }
            }
        }
    }


    @Override
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
            if (status)
            {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e)
        {
            System.out.println(e);
        } finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                } catch (SQLException ignore)
                {
                }
            }
        }
        return status;
    }


    @Override
    public void delete(int id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        } finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                } catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public boolean recordExists(User user)
    {
        boolean recordExists = true;
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            recordExists = rs.next();
        } catch (SQLException e)
        {
            System.out.println(e);
        } finally
        {            
            if (conn != null)
            {
                try
                {
                    conn.close();
                } catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
        return recordExists;
    }
    
}
