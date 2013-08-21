package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.models.League;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database access for the league table.
 * 
 */
public class LeagueDaoImpl implements LeagueDao
{
    @Override
    public void save(League league)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO league (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, league.getName());
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
                } catch (SQLException ignore)
                {
                }
            }
        }
    }
    
    @Override
    public List<League> getAllLeagues()
    {
        Connection conn = ConnectionProvider.createConnection();
        List<League> leagues = new ArrayList();
        try
        {
            String sql = "SELECT * FROM league";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                League league = new League();
                league.setId(rs.getInt("id"));
                league.setName(rs.getString("name"));
                leagues.add(league);
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
    
    @Override
    public void delete(int id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM league WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
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

    @Override
    public League getById(int id)
    {
        League league = new League();
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "SELECT name FROM league WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            league.setId(id);
            league.setName(rs.getString("name"));
     
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
        return league;
    }
}
