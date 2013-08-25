

package fi.cs.helsinki.glindstr.soccerdb.models;

import java.io.Serializable;

/**
 * This class represents a record of the membership table.
 * A membership defines which teams play in a league a certain season.
 */
public class Membership implements Serializable
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
    
    /**
     * the name of the season
     */
    private String seasonName;
    
    /**
     * the name of the team
     */
    private String teamName;
    
    /**
     * the name of the league
     */
    private String leagueName;

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

    public String getLeagueName()
    {
        return leagueName;
    }

    public String getSeasonName()
    {
        return seasonName;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setLeagueName(String league_name)
    {
        this.leagueName = league_name;
    }

    public void setSeasonName(String season_name)
    {
        this.seasonName = season_name;
    }

    public void setTeamName(String team_name)
    {
        this.teamName = team_name;
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
