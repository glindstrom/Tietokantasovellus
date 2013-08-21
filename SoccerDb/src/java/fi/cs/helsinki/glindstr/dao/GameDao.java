package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.models.Game;
import fi.cs.helsinki.glindstr.models.Team;
import java.util.List;

/**
 * An interface for database operations on the game table. The MemebershipDao
 * interface provides a method for gaining a list of all games.
 *
 */
public interface GameDao extends BaseDao<Game>
{

    /**
     * Returns a list containing all leagues in the database.
     *
     * @return a list of all leagues
     */
    List<Game> getAllGames();

    /**
     * Returns a list of teams playing in a specified league in a specified season.
     * @param leagueId the id of the specified league
     * @param seasonId the id of the specified season
     * @return a list of teams
     */
    List<Team> getTeamsByLeagueAndSeason(int leagueId, int seasonId);
}
