package classes;

import java.io.Serializable;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * Team.java
 * Version 1.0
 * Team consisting of a team name and a team id
 */
public class Team implements Serializable  //series of ones and zeros
{
    private String teamName;
    private int teamId;

    //constructor
    public Team(String teamName, int teamId) 
    {
        this.teamName = teamName;
        this.teamId = teamId;        
    }
    
    //getter and setter
    public String getTeamName() 
    {
        return teamName;
    }

    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public int getTeamId() 
    {
        return teamId;
    }

    public void setTeamId(int teamId) 
    {
        this.teamId = teamId;
    }
}  //end class
