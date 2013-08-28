package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.dbconnection.ConnectionProvider;
import fi.cs.helsinki.glindstr.soccerdb.models.Standing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class retrieves the standings from the database.
 *
 */
public class StandingsDaoImpl implements StandingsDao
{

    @Override
    public List<Standing> getAllStandings(int seasonId, int leagueId)
    {
        Connection conn = ConnectionProvider.createConnection();
        List<Standing> standings = new ArrayList();
        try
        {
            String sql = "SELECT distinct name AS TEAM, (SELECT COUNT(*) FROM GAME g WHERE (t.id = g.home_team OR t.ID = g.AWAY_TEAM) AND g.home_score is not null "
                    + "AND g.away_score is not null AND g.SEASON_ID=? AND g.LEAGUE_ID=?) as GP, "
                    + "(SELECT count(*) FROM game g WHERE ((t.id = g.home_team and home_score > away_score)) "
                    + "OR (t.id=g.away_team and away_score > home_score) AND g.SEASON_ID=? AND g.LEAGUE_ID=?)  as w, "
                    + "(SELECT count(*) FROM game g WHERE (t.id = g.home_team OR t.id = g.away_team) AND home_score = away_score "
                    + "AND g.SEASON_ID=? AND g.LEAGUE_ID=?) as d, "
                    + "(SELECT count(*) FROM game g WHERE ((t.id = g.home_team and home_score < away_score)) "
                    + "OR (t.id=g.away_team and away_score < home_score) AND g.SEASON_ID=? AND g.LEAGUE_ID=?) as l, "
                    + "(coalesce((SELECT SUM(home_score) FROM GAME g where t.id = g.HOME_TEAM AND g.SEASON_ID=? AND g.LEAGUE_ID=?),0) "
                    + "+ coalesce((SELECT SUM(away_score) FROM GAME g where t.id = g.AWAY_TEAM AND g.SEASON_ID=? AND g.LEAGUE_ID=?),0)) AS GF, "
                    + "(coalesce((SELECT SUM(home_score) FROM GAME g where t.id = g.AWAY_TEAM AND g.SEASON_ID=? AND g.LEAGUE_ID=?),0 ) "
                    + "+ coalesce((SELECT SUM(away_score) FROM GAME g where t.id = g.HOME_TEAM AND g.SEASON_ID=? AND g.LEAGUE_ID=?),0)) AS GA, "
                    + "3*(SELECT count(*) FROM game g "
                    + "WHERE ((t.id = g.home_team and home_score > away_score) OR (t.id=g.away_team and away_score > home_score)) AND g.SEASON_ID=? AND g.LEAGUE_ID=?) "
                    + "+(SELECT count(*) FROM game g WHERE (t.id = g.home_team OR t.id = g.away_team) AND home_score = away_score AND g.SEASON_ID=? AND g.LEAGUE_ID=?) as PTS "
                    + "FROM team t INNER JOIN membership on t.id = membership.team_id "
                    + "LEFT OUTER JOIN GAME g ON (g.home_team = t.ID OR g.AWAY_TEAM = t.ID) "
                    + "WHERE membership.SEASON_ID=? AND membership.LEAGUE_ID=? ORDER BY PTS DESC, TEAM";

            PreparedStatement ps = conn.prepareStatement(sql);
            setLeagueAndSeasonId(ps, 11, leagueId, seasonId);
            ResultSet rs = ps.executeQuery();
            int rank = 1;
            while (rs.next())
            {
                Standing standing = new Standing();
                standing.setRank(rank);
                standing.setTeamName(rs.getString("team"));
                standing.setGamesPlayed(rs.getInt("gp"));
                standing.setWins(rs.getInt("w"));
                standing.setDraws(rs.getInt("d"));
                standing.setLosses(rs.getInt("l"));
                standing.setGoalsFor(rs.getInt("gf"));
                standing.setGoalsAgainst(rs.getInt("ga"));
                standing.setTeamPoints(rs.getInt("pts"));
                rank++;
                standings.add(standing);
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
        return standings;
    }

    public static void main(String[] args)
    {
        StandingsDao dao = new StandingsDaoImpl();
        List<Standing> standings = dao.getAllStandings(2, 4);
        System.out.println(standings.size());
    }

    private void setLeagueAndSeasonId(PreparedStatement ps, int numberOfPairs, int leagueId, int seasonId) throws SQLException
    {
        int i = 1;
        while (i <= 2 * numberOfPairs)
        {
            ps.setInt(i, seasonId);
            i++;
            ps.setInt(i, leagueId);
            i++;
        }

    }
}
