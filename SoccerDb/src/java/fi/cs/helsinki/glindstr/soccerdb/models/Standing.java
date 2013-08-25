

package fi.cs.helsinki.glindstr.soccerdb.models;

/**
 * This class represents a row of the standings query.
 * 
 */
public class Standing 
{
    /**
     * the rank of the team
     */
    private int rank;
    
    /**
     * the team name
     */
    private String teamName;
    
    /**
     * the total number of games the team has played
     */
    private int gamesPlayed;
    
    /**
     * the number of wins
     */
    private int wins;
    
    /**
     * the number of draws
     */
    private int draws;
    
    /**
     * the number of losses
     */
    private int losses;
    
    /**
     * the total number of goals the team has scored
     */
    private int goalsFor;
    
    /**
     * the total number of goals the team has conceded
     */
    private int goalsAgainst;
    
    /**
     * the number of points the team has earned
     */
    private int teamPoints;    

    public int getGamesPlayed()
    {
        return gamesPlayed;
    }

    public int getGoalsAgainst()
    {
        return goalsAgainst;
    }

    public int getGoalsFor()
    {
        return goalsFor;
    }

    public int getRank()
    {
        return rank;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public int getTeamPoints()
    {
        return teamPoints;
    }

    public void setGamesPlayed(int gamesPlayed)
    {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGoalsAgainst(int goalsAgainst)
    {
        this.goalsAgainst = goalsAgainst;
    }

    public void setGoalsFor(int goalsFor)
    {
        this.goalsFor = goalsFor;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public void setTeamPoints(int teamPoints)
    {
        this.teamPoints = teamPoints;
    }    

    public int getDraws()
    {
        return draws;
    }

    public int getLosses()
    {
        return losses;
    }

    public int getWins()
    {
        return wins;
    }

    public void setDraws(int draws)
    {
        this.draws = draws;
    }

    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    public void setWins(int wins)
    {
        this.wins = wins;
    }        
}
