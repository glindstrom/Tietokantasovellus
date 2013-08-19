package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.models.Membership;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database access for the membership table.
 *
 * @author Gabriel
 */
public class MembershipDaoImpl implements MembershipDao
{

    @Override
    public void save(Membership membership)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO membership (league_id, season_id, team_id) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, membership.getLeagueId());
            ps.setInt(2, membership.getSeasonId());
            ps.setInt(3, membership.getTeamId());
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
    public List<Membership> getAllMemberships()
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Membership> memberships = new ArrayList();
        try
        {
            String sql = "SELECT * FROM membership";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Membership membership = new Membership();
                membership.setId(rs.getInt("id"));
                membership.setLeagueId(rs.getInt("league_id"));
                membership.setSeasonId(rs.getInt("season_id"));
                membership.setTeamId(rs.getInt("team_id"));
                memberships.add(membership);
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
        return memberships;
    }

    @Override
    public void delete(int id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM membership WHERE id=?";
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
}
