/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.glindstr.dao;

import fi.cs.helsinki.glindstr.models.User;

/**
 * An interface for database operations on the users table. 
 * The UserDao interface provides a method for validating a user.
 */
public interface UserDao extends BaseDao<User>
{

    /**
     * Checks if the user has registered.
     *
     * @param user the user to be validated
     * @return true if the username and password matches the database.
     */
    boolean validateUser(User user);
}
