

package fi.cs.helsinki.glindstr.models;


/**
 * This class represents records of the season table.
 * 
 */
public class Season 
{
    /**
     * the id number of the season
     */
    private int id;
    
    /**
     * the name of the season
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
