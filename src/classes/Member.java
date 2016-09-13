package classes;

import java.io.Serializable;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * Member.java
 * Version 1.0
 * Abstract class for Member
 */
public abstract class Member implements Serializable  //series of ones and zeros
{    
    //instance variables
    private int memberId;
    private String firstName;
    private String lastName;    
    private Address address;  //declare Address obj    
    private String mobile;
    private String email;
    private double fee;
    private int totalRaces;
    private int totalWins;
    private Team team;
    
    //constructor
    public Member(int memberId, String firstName, String lastName, 
                    String streetNo, String streetName, 
                    String suburb, int postCode, 
                    String mobile, String email, 
                    int totalRaces, int totalWins) 
    {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        
        //instantiate address object
        this.address = new Address(streetNo, streetName, suburb, postCode);
                
        this.mobile = mobile;
        this.email = email;
        this.totalRaces = totalRaces;
        this.totalWins = totalWins;
    }

    //getter and setter   
    public int getMemberId() 
    {
        return memberId;
    }    

    public void setMemberId(int memberId) 
    {
        this.memberId = memberId;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public Address getAddress() 
    {
        return address;
    }

    public void setAddress(Address address) 
    {
        this.address = address;
    }

    public String getMobile() 
    {
        return mobile;
    }

    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getEmail() 
    {
        return email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public double getFee() 
    {
        return fee;
    }

    public void setFee(double fee) 
    {
        this.fee = fee;
    }

    public int getTotalRaces() 
    {
        return totalRaces;
    }

    public void setTotalRaces(int totalRaces) 
    {
        this.totalRaces = totalRaces;
    }

    public int getTotalWins() 
    {
        return totalWins;
    }

    public void setTotalWins(int totalWins) 
    {
        this.totalWins = totalWins;
    }

    public Team getTeam()
    {
        return team;
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }
            
    /**
     * Calculate the win percentage
     * @return   Win percentage of races presented in a String
     */
    public String calcWinPercentage()
    {
        double winPercentage = 0;
        
        //check if total races is zero. can't divide with zero
        if (this.totalRaces > 0)
        {
            //calculate percentage
            winPercentage = (this.totalWins * 100 / (this.getTotalRaces() * 1.0));            
        }   
        
        String formattedString = String.format("%.2f", winPercentage);
        
        return formattedString;
    }
    
    //abstract methods
    public abstract void calcFee();  
    public abstract String getMemberType();
    
    //display details
    public String display()
    {        
        String s = "Team name: " + this.getTeam().getTeamName() + 
                "\nTeam id: " + this.getTeam().getTeamId() +
                "\nMember ID: " + this.memberId + 
                "\nFirst name: " + this.firstName + 
                "\nLast name: " + this.lastName +
                "\nAddress: " + this.address.displayAddress() +
                "\nMobile: " + this.mobile +
                "\nEmail: " + this.email +
                "\nTotal Race(s): " + this.totalRaces +
                "\nTotal Win(s): " + this.totalWins +
                "\nWin Percentage: " + this.calcWinPercentage() + "%" +
                "\nFee: $" + String.format("%.2f", this.fee);    
        
        return s;
    }
    
}  //end class
