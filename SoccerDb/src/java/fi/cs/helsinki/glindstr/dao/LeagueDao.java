
package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.models.League;
import java.util.List;

/**
 * An interface for database operations on the league table.
 * The LeagueDao interface provides a method for gaining a list of all leagues.
 * 
 */
public interface LeagueDao extends BaseDao<League>
{
    /**
     * Returns a list containing all leagues in the database.
     * @return a list with all leagues
     */
    List<League> getAllLeagues();
    
    /**
     * Returns a league based on the id. 
     * @param id the id of the record
     * @return a record of a the league table
     */
     League getById(int id);
}
