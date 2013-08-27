

package fi.cs.helsinki.glindstr.soccerdb.models;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class represents a record of the game table.
 * 
 */
public class Game implements Serializable
{
    /**
     * the game id
     */
    private int id;
    /**
     * the id of the home team
     */
    private int homeTeamId;
    /**
     * the id of the away team
     */
    private int awayTeamId;
    /**
     * the score of the home team
     */
    private Integer homeScore = null;
    /**
     * the score of the away team
     */
    private Integer awayScore = null;
    /**
     * the id of the last user to edit the record
     */
    private int editedBy;
    /**
     * the username of the last user to edit the record
     */
    private String editedByUsername;
    /**
     * the date of the game
     */
    private Date gameDate;
    /**
     * the point in time the record was last edited
     */
    private String timestamp;
    
    /**
     * the name of the home team
     */
    private String homeTeamName;
    /**
     * the name of the away team
     */
    private String awayTeamName;
    /**
     * the id of the league the game is played in
     */
    private int leagueId;
    /**
     * the name of the league the game is played in
     */
    private String leagueName;
    /**
     * the id of the season the game takes place in
     */
    private int seasonId;
    /**
     * the name of the season the game takes place in
     */
    private String seasonName;

    public int getLeagueId()
    {
        return leagueId;
    }

    public String getLeagueName()
    {
        return leagueName;
    }

    public int getSeasonId()
    {
        return seasonId;
    }

    public String getSeasonName()
    {
        return seasonName;
    }

    public void setLeagueId(int leagueId)
    {
        this.leagueId = leagueId;
    }

    public void setLeagueName(String leagueName)
    {
        this.leagueName = leagueName;
    }

    public void setSeasonId(int seasonId)
    {
        this.seasonId = seasonId;
    }

    public void setSeasonName(String seasonName)
    {
        this.seasonName = seasonName;
    }
    
    
    public void setHomeTeamName(String homeTeamName)
    {
        this.homeTeamName = homeTeamName;
    }

    public void setAwayTeamName(String awayTeamName)
    {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamName()
    {
        return homeTeamName;
    }

    public String getAwayTeamName()
    {
        return awayTeamName;
    }    
    
    public String getAwayScoreAsString()
    {
        if (awayScore == null)
        {
            return "";
        }
        return awayScore.toString();
    }
    
    public int getAwayScore()
    {
        return awayScore;
    }

    public int getAwayTeamId()
    {
        return awayTeamId;
    }

    public int getEditedBy()
    {
        return editedBy;
    }

    public Date getGameDate()
    {
        return gameDate;
    }
    
    public int getHomeScore()
    {
        return homeScore;
    }

    public String getHomeScoreAsString()
    {
        if (homeScore == null)
        {
            return "";
        }
        return homeScore.toString();
    }

    public int getHomeTeamId()
    {
        return homeTeamId;
    }

    public int getId()
    {
        return id;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }        

    public void setAwayScore(int awayScore)
    {
        this.awayScore = awayScore;
    }

    public void setAwayTeamId(int awayTeam)
    {
        this.awayTeamId = awayTeam;
    }

    public void setHomeScore(int homeScore)
    {
        this.homeScore = homeScore;
    }

    public void setHomeTeamId(int homeTeam)
    {
        this.homeTeamId = homeTeam;
    }

    public void setEditedBy(int editedBy)
    {
        this.editedBy = editedBy;
    }

    public void setGameDate(Date gameDate)
    {
        this.gameDate = gameDate;
    }

    public void setId(int id)
    {
        this.id = id;
    }       

    public void setEditedByUsername(String editedByUsername)
    {
        this.editedByUsername = editedByUsername;
    }

    public String getEditedByUsername()
    {
        return editedByUsername;
    }
    
    public String getDateAsString()
    {
        return this.gameDate.toString();
    }
    
    public void setDateFromString(String date)
    {
        this.gameDate = Date.valueOf(date);
    }
}
