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
            String sql = "SELECT name AS TEAM, \n"
                    + "(SELECT COUNT(*) FROM GAME g WHERE t.id = g.home_team OR t.ID = g.AWAY_TEAM) as GP, "
                    + "(SELECT count(*) FROM game g WHERE ((t.id = g.home_team and home_score > away_score)) "
                    + "OR (t.id=g.away_team and away_score > home_score)) as w, "
                    + "(SELECT count(*) FROM game g WHERE (t.id = g.home_team OR t.id = g.away_team) AND home_score = away_score) as d, "
                    + "(SELECT count(*) FROM game g WHERE ((t.id = g.home_team and home_score < away_score)) "
                    + "OR (t.id=g.away_team and away_score < home_score)) as l, "
                    + "(coalesce((SELECT SUM(home_score) FROM GAME g where t.id = g.HOME_TEAM),0) "
                    + "+ coalesce((SELECT SUM(away_score) FROM GAME g where t.id = g.AWAY_TEAM),0)) AS GF, "
                    + "(coalesce((SELECT SUM(home_score) FROM GAME g where t.id = g.AWAY_TEAM),0) "
                    + "+ coalesce((SELECT SUM(away_score) FROM GAME g where t.id = g.HOME_TEAM),0)) AS GA, "
                    + "3*(SELECT count(*) FROM game g "
                    + "WHERE ((t.id = g.home_team and home_score > away_score)) OR (t.id=g.away_team and away_score > home_score)) "
                    + "+(SELECT count(*) FROM game g WHERE (t.id = g.home_team OR t.id = g.away_team) AND home_score = away_score) as PTS "
                    + "FROM team t INNER JOIN membership on t.id = membership.team_id "
                    + "WHERE membership.LEAGUE_ID = 4 AND membership.SEASON_ID = 1 ORDER BY PTS DESC, TEAM";
            
            PreparedStatement ps = conn.prepareStatement(sql);
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
        return standings;
    }
}
