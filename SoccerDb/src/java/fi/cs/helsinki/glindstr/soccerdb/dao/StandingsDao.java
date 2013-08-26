
package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.models.Standing;
import java.util.List;

/**
 * An interface for retrieving the standings from the database.
 * Provides a method for getting a list with all standings.
 */
public interface StandingsDao
{
   /**
    * Returns a list containing the standings for one season of a league.
    * @param seasonId the selected season
    * @param leagueId the selected league
    * @return a list containing the standings
    */
    List<Standing> getAllStandings(int seasonId, int leagueId);    
}
