package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.models.Game;
import java.util.List;

/**
 * An interface for database operations on the game table. The GameDao
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
     * Returns a game record from the database based on the id.
     * @param id the id of the record
     * @return a game record
     */
    Game getGameById(int id);
    
    /**
     * Updates the result of the specified game.
     * @param game the game of which the score should be updated
     */
    void updateScore(Game game);
}
