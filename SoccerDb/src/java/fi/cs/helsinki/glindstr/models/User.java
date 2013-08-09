

package fi.cs.helsinki.glindstr.models;

/**
 * This class represents records of the users table.
 * 
 */
public class User 
{
    private String username;
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
}
