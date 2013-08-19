/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.models.Season;
import java.util.List;

/**
 * An interface for database operations on the season table.
 * The SeasonDao interface provides a method for gaining a list of all seasons.
 * 
 */
public interface SeasonDao extends BaseDao<Season>
{
    /**
     * Returns a list containing all seasons in the database.
     * @return a list with all seasons
     */
    List<Season> getAllSeasons();
}
