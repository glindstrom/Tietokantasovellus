package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.soccerdb.models.Season;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database access for the season table.
 *
 */
public class SeasonDaoImpl implements SeasonDao
{

    @Override
    public void save(Season season)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO season (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, season.getName());
            ps.executeUpdate();
            conn.close();
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
                } catch (SQLException ignore)
                {
                }
            }
        }
    }

    @Override
    public List<Season> getAllSeasons()
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Season> seasons = new ArrayList();
        try
        {
            String sql = "SELECT * FROM season";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Season season = new Season();
                season.setId(rs.getInt("id"));
                season.setName(rs.getString("name"));
                seasons.add(season);
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
                } catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
        return seasons;
    }

    @Override
    public void delete(int id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM season WHERE id=?";
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
    public Season getById(int id)
    {
        Season season = new Season();
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "SELECT name FROM season WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            season.setId(id);
            season.setName(rs.getString("name"));
     
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
        return season;
    }
     
    @Override
    public boolean recordExists(Season season)
    {
        boolean recordExists = true;
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "SELECT name FROM season WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, season.getName());
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
