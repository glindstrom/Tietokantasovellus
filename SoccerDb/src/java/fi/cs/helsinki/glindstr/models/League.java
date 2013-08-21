

package fi.cs.helsinki.glindstr.models;

import java.io.Serializable;


/**
 * This class represents records of the league table.
 * 
 */
public class League implements Serializable
{
    /**
     * the id number of the league
     */
    private int id;
    
    /**
     * the name of the league
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
