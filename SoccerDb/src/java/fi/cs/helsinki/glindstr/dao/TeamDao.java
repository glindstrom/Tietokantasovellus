package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.models.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database access for the team table.
 * @author Gabriel
 */
public class TeamDao
{
    /**
     * Adds a new team to the database.
     * @param team the team to be added
     */
    public void addTeam(Team team)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO league (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, team.getName());
            ps.executeUpdate();
            conn.close();
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
    }
    
    /**
     * Returns a list containing all leagues in the database
     * @return a list of all leagues
     */
    public List<Team> getAllTeams()
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Team> leagues = new ArrayList();
        try
        {
            String sql = "SELECT * FROM league";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                leagues.add(team);
            }
        } 
        catch (SQLException e)
        {
            System.out.println(e);
        } 
        finally
        {
            if (conn != null)
            {
                try
                {
                  conn.close();
                } 
                catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
        return leagues;
    }
    
    public void deleteTeam(String id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM team WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } 
        catch (SQLException e)
        {
            System.out.println(e);
        } 
        finally
        {
            if (conn != null)
            {
                try
                {
                  conn.close();
                } 
                catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        } 
    }
}
