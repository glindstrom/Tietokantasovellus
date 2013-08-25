

package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.soccerdb.models.Game;
import fi.cs.helsinki.glindstr.soccerdb.models.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class provides database access for the game table.
 * 
 */
public class GameDaoImpl implements GameDao
{

    @Override
    public List<Game> getAllGames()
    {
        
       Connection conn = ConnectionProvider.createConnection();
        List<Game> games = new ArrayList();
        try
        {
            String sql = "SELECT game.*, t1.name AS home_team_name, t2.name AS away_team_name, "
                    + "u.username AS edited_by_user, l.name as league_name, s.name as season_name "
                    + "FROM game "
                    + "INNER JOIN team t1 ON "
                    + "game.home_team = t1.id "
                    + "INNER JOIN team t2 ON "
                    + "game.away_team = t2.id "
                    + "INNER JOIN league l ON game.league_id = l.id "
                    + "INNER JOIN season s ON game.season_id = s.id "
                    + "INNER JOIN users u ON edited_by = u.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setHomeTeamName(rs.getString("home_team_name"));
                game.setAwayTeamName(rs.getString("away_team_name"));
                game.setHomeScore(rs.getInt("home_score"));
                game.setAwayScore(rs.getInt("away_score"));
                game.setGameDate(rs.getDate("game_date"));
                game.setEditedByUsername(rs.getString("edited_by_user"));
                game.setTimestamp(rs.getTimestamp("time_edited").toString());
                game.setSeasonName(rs.getString("season_name"));
                game.setLeagueName(rs.getString("league_name"));
                games.add(game);
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
        return games;
    }

    @Override
    public void save(Game game)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "INSERT INTO game (home_team, away_team, home_score, away_score, game_date, edited_by, time_edited, league_id, season_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, game.getHomeTeamId());
            ps.setInt(2, game.getAwayTeamId());
            ps.setInt(3, game.getHomeScore());
            ps.setInt(4, game.getAwayScore());
            ps.setDate(5, game.getGameDate());
            ps.setInt(6, game.getEditedBy());
            ps.setTimestamp(7, getCurrentTimestamp());
            ps.setInt(8, game.getLeagueId());
            ps.setInt(9, game.getSeasonId());
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
    public void delete(int id)
    {
       Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM game WHERE id=?";
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
    
    /**
     * Returns the current timestamp
     * @return a timestamp indicating the current time
     */
    private Timestamp getCurrentTimestamp()
    {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }

    @Override
    public List<Team> getTeamsByLeagueAndSeason(int leagueId, int seasonId)
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Team> teams = new ArrayList();
        try
        {
            String sql = "SELECT team_id AS id, team.name AS name FROM membership, team "
                    + "WHERE league_id = ? AND season_id = ? AND membership.team_id = team.id";
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
    
    
}
