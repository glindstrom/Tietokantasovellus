

package fi.cs.helsinki.glindstr.models;

import java.io.Serializable;


/**
 * This class represents records of the team table.
 * 
 */
public class Team implements Serializable
{
    /**
     * the id number of the team
     */
    private int id;
    
    /**
     * the name of the team
     */
    private String name;
     
    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
        
}
