package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.soccerdb.models.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database access for the team table.
 *
 * @author Gabriel
 */
public class TeamDaoImpl implements TeamDao
{
    @Override
    public void save(Team team)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO team (name) VALUES (?)";
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

    @Override
    public List<Team> getAllTeams()
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Team> teams = new ArrayList();
        try
        {
            String sql = "SELECT * FROM team";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                teams.add(team);
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
                } catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
        return teams;
    }

    @Override
    public void delete(int id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM team WHERE id=?";
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
}
