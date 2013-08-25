/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.models.Team;
import java.util.List;

/**
 * An interface for database operations on the team table.
 * The TeamDao interface provides a method for gaining a list of all teams.
 * 
 */
public interface TeamDao extends BaseDao<Team>
{

    /**
     * Returns a list containing all leagues in the database.
     *
     * @return a list of all leagues
     */
    List<Team> getAllTeams();
}
