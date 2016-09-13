package classes;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * Abled.java
 * Version 1.0
 * Class for Abled, inherit from Member. Models an abled skier
 */
public class Abled extends Member
{
    //class constants
    public static final String HIGH = "High";
    public static final String LOW = "Low";
    
    //instance variables
    private boolean isFrontRunner;
    private String experience;

    //constructor
    public Abled(boolean isFrontRunner, String experience, int memberId, 
                String firstName, String lastName, 
                String streetNo, String streetName, 
                String suburb, int postCode,  
                String mobile, String email, 
                int totalRaces, int totalWins) 
    {
        super(memberId, firstName, lastName, streetNo, streetName, 
                suburb, postCode, mobile, email, 
                totalRaces, totalWins);
        
        this.isFrontRunner = isFrontRunner;
        this.experience = experience;
        
        this.calcFee();    
    }

    //getter and setter
    public boolean getIsFrontRunner() 
    {
        return isFrontRunner;
    }

    public void setIsFrontRunner(boolean isFrontRunner) 
    {
        this.isFrontRunner = isFrontRunner;
    }

    public String getExperience() 
    {
        return experience;
    }

    public void setExperience(String experience) 
    {
        this.experience = experience;
    }    
    
    //other methods
    @Override
    public void calcFee() 
    {
        if (isFrontRunner)
        {
            //decide the discount based on experience
            if (experience.equals(Abled.HIGH))
            {
                this.setFee((Fee.BASE_FEE - (Fee.BASE_FEE * Fee.DISCOUNT_ABLED_FRONT_RUNNER / 100)) - Fee.DISCOUNT_HIGH_FRONT_RUNNER);                
            }
            else  //low
            {
                this.setFee((Fee.BASE_FEE - (Fee.BASE_FEE * Fee.DISCOUNT_ABLED_FRONT_RUNNER / 100)) - Fee.DISCOUNT_LOW_FRONT_RUNNER); 
                
            }         
        }
        else
        {
            this.setFee(Fee.BASE_FEE);             
        }         
    }  
    
    @Override
    public String getMemberType()
    {
        return "A";        
    }
    
    /**
     * Get the display whether member is a frontrunner or not. "Y" or "N"
     * @return   String containing either  "Y" or "N" based on whether member is front runner or not.
     */
    public String getIsFrontRunnerDisplay()
    {
        if(isFrontRunner)
        {
            return "Y";
        }
        else
        {
            return "N";            
        }        
    }
    
    //display details
    @Override
    public String display()
    {        
        String s = super.display();
        
        s += "\nMembership type: " + getMemberType();
        
        if (isFrontRunner)
        {
            s += "\nFront runner: " + getIsFrontRunnerDisplay() + "\nExperience as front runner: " + experience;            
        }
        else
        {
            s += "\nFront runner: " + getIsFrontRunnerDisplay();             
        }        
        
        return s;
    } 
    
}  //end class
