/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.glindstr.soccerdb.dao;

import fi.cs.helsinki.glindstr.soccerdb.models.Membership;
import java.util.List;

/**
 * An interface for database operations on the membership table.
 * The MemebershipDao interface provides a method for gaining a list of all memberships.
 * 
 */
public interface MembershipDao extends BaseDao<Membership>
{

    /**
     * Returns a list containing all leagues in the database.
     *
     * @return a list of all leagues
     */
    List<Membership> getAllMemberships();
}
