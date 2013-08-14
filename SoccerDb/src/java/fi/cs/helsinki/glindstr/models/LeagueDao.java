

package fi.cs.helsinki.glindstr.models;

import fi.cs.helsinki.glindstr.dbconnection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LeagueDao 
{
     public void addUser(League league)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO league (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, league.getName());
            ps.executeUpdate();
            conn.close();
        } 
        catch (SQLException e)
        {
            System.out.println(e);
        }        
    }
}
