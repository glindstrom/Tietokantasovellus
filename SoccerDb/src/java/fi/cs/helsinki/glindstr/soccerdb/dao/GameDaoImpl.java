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
import org.apache.derby.client.am.Types;

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
                    + "INNER JOIN users u ON edited_by = u.id "
                    + "ORDER BY game_date DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Game game = createGameRecord(rs);
                games.add(game);
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
            if (game.getHomeScore() < 0 || game.getAwayScore() < 0)
            {
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.INTEGER);
            }
            else
            {
                ps.setInt(3, game.getHomeScore());
                ps.setInt(4, game.getAwayScore());
            }
            ps.setDate(5, game.getGameDate());
            ps.setInt(6, game.getEditedBy());
            ps.setTimestamp(7, getCurrentTimestamp());
            ps.setInt(8, game.getLeagueId());
            ps.setInt(9, game.getSeasonId());
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
    public void delete(int id)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "DELETE FROM game WHERE id=?";
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

    /**
     * Returns the current timestamp
     *
     * @return a timestamp indicating the current time
     */
    private Timestamp getCurrentTimestamp()
    {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }

   

    @Override
    public Game getGameById(int id)
    {
        Game game = null;
        Connection conn = ConnectionProvider.createConnection();
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
                    + "INNER JOIN users u ON edited_by = u.id "
                    + "WHERE game.id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            game = createGameRecord(rs);
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
        return game;
    }

    @Override
    public void updateScore(Game game)
    {
        Connection conn = ConnectionProvider.createConnection();
        try
        {
            String sql = "UPDATE game SET home_score=?, away_score=?, edited_by=?, time_edited=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, game.getHomeScore());
            ps.setInt(2, game.getAwayScore());
            ps.setInt(3, game.getEditedBy());
            ps.setTimestamp(4, getCurrentTimestamp());
            ps.setInt(5, game.getId());
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

    /**
     * Creates a new game record based on a query.
     *
     * @param rs the result set
     * @return a new game record
     * @throws SQLException
     */
    private Game createGameRecord(ResultSet rs) throws SQLException
    {
        Game game = new Game();
        game.setId(rs.getInt("id"));
        game.setHomeTeamName(rs.getString("home_team_name"));
        game.setAwayTeamName(rs.getString("away_team_name"));
        int homeScore = rs.getInt("home_score");
        int awayScore = rs.getInt("away_score");
        if (!rs.wasNull())
        {
            game.setHomeScore(homeScore);
            game.setAwayScore(awayScore);
        }

        game.setGameDate(rs.getDate("game_date"));
        game.setEditedByUsername(rs.getString("edited_by_user"));
        game.setTimestamp(rs.getTimestamp("time_edited").toString());
        game.setSeasonName(rs.getString("season_name"));
        game.setLeagueName(rs.getString("league_name"));
        return game;
    }

}
