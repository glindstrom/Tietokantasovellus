package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.soccerdb.models.Membership;
import fi.cs.helsinki.glindstr.soccerdb.models.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database access for the membership table.
 *
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
                }
                catch (SQLException ignore)
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
            String sql = "SELECT membership.*, s.name as season_name, l.name AS league_name, t.name as team_name"
                    + " FROM membership"
                    + " INNER JOIN season s ON"
                    + " membership.season_id = s.id"
                    + " INNER JOIN league l ON"
                    + " membership.league_id = l.id"
                    + " INNER JOIN team t ON"
                    + " membership.team_id = t.id ORDER BY league_name, team_name";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Membership membership = new Membership();
                membership.setId(rs.getInt("id"));
                membership.setLeagueId(rs.getInt("league_id"));
                membership.setSeasonId(rs.getInt("season_id"));
                membership.setTeamId(rs.getInt("team_id"));
                membership.setLeagueName(rs.getString("league_name"));
                membership.setSeasonName(rs.getString("season_name"));
                membership.setTeamName(rs.getString("team_name"));
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
                }
                catch (SQLException e)
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

    @Override
    public List<Team> getTeamsByLeagueAndSeason(int leagueId, int seasonId)
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Team> teams = new ArrayList();
        try
        {
            String sql = "SELECT team_id AS id, team.name AS name FROM membership, team "
                    + "WHERE league_id = ? AND season_id = ? AND membership.team_id = team.id "
                    + "ORDER BY name";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, seasonId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                teams.add(team);
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
        return teams;
    }

    @Override
    public boolean recordExists(Membership membership)
    {
        boolean recordExists = true;
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "SELECT id FROM membership WHERE team_id = ? AND season_id = ? AND league_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, membership.getTeamId());
            ps.setInt(2, membership.getSeasonId());
            ps.setInt(3, membership.getLeagueId());
            ResultSet rs = ps.executeQuery();
            recordExists = rs.next();
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
        return recordExists;
    }
}
