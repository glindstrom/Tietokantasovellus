

package fi.cs.helsinki.glindstr.models;

/**
 * This class represents a record of the membership table.
 * A membership defines which teams play in a league a certain season.
 */
public class Membership 
{
    /**
     * the id number of the membership
     */
    private int id;
    
    /**
     * the id number of the league
     */
    private int leagueId;
    
    /**
     * the id number of the season
     */
    private int seasonId;
    
    /**
     * the id number of the team
     */
    private int teamId;

    public int getId()
    {
        return id;
    }

    public int getLeagueId()
    {
        return leagueId;
    }

    public int getSeasonId()
    {
        return seasonId;
    }

    public int getTeamId()
    {
        return teamId;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setLeagueId(int leagueId)
    {
        this.leagueId = leagueId;
    }

    public void setSeasonId(int seasonId)
    {
        this.seasonId = seasonId;
    }

    public void setTeamId(int teamId)
    {
        this.teamId = teamId;
    }        
}
