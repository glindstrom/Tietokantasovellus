

package fi.cs.helsinki.glindstr.models;

import java.io.Serializable;

/**
 * This class represents a record of the users table.
 * 
 */
public class User implements Serializable
{
    /**
     * the id number of the user
     */
    private int id;
    /**
     * the user's alias
     */
    private String username;
    /**
     * the user's password
     */
    private String password;

    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPass()
    {
        return password;
    }

    public void setPass(String pass)
    {
        this.password = pass;
    }   

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    
}
