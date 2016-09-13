package classes;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * Disabled.java
 * Version 1.0
 * Class for Disabled, inherit from Member. Models a disabled skier
 */
public class Disabled extends Member
{
    //class constants
    public static final String VISUAL_IMPAIRED = "Visually impaired";
    public static final String STANDING = "Standing";
    public static final String SITTING = "Sitting";
        
    //instance variables
    private String disabilityType;
    private boolean isFrontRunnerNeeded;  
        
    //constructor    
    public Disabled(String disabilityType, boolean isFrontRunnerNeeded, 
                    int memberId, String firstName, String lastName, 
                    String streetNo, String streetName, 
                    String suburb, int postCode, 
                    String mobile, String email, 
                    int totalRaces, int totalWins)
    {
        super(memberId, firstName, lastName, streetNo, streetName, 
                suburb, postCode, mobile, email, 
                totalRaces, totalWins);
        
        this.disabilityType = disabilityType;
        this.isFrontRunnerNeeded = isFrontRunnerNeeded;
        
        this.calcFee();        
    } 

    //getter and setter
    public String getDisabilityType() 
    {
        return disabilityType;
    }

    public void setDisabilityType(String disabilityType) 
    {
        this.disabilityType = disabilityType;
    }

    public boolean getIsFrontRunnerNeeded() 
    {
        return isFrontRunnerNeeded;
    }

    public void setIsFrontRunnerNeeded(boolean isFrontRunnerNeeded) 
    {
        this.isFrontRunnerNeeded = isFrontRunnerNeeded;
    }   
    
    @Override
    public void calcFee() 
    {
        if (isFrontRunnerNeeded)
        {
            this.setFee(Fee.BASE_FEE - (Fee.BASE_FEE * Fee.DISCOUNT_DISABLED_NEED_FRONT_RUNNER / 100));            
        }
        else
        {
            this.setFee(Fee.BASE_FEE - (Fee.BASE_FEE * Fee.DISCOUNT_DISABLED / 100));             
        }    
    }      
    
    @Override
    public String getMemberType()
    {
        return "D";        
    } 
    
    /**
     * Get the display whether member need a frontrunner or not. "Y" or "N"
     * @return   String containing either  "Y" or "N" based on whether member need front runner or not.
     */
    public String getNeedFrontrunnerDisplay()
    {
        if(isFrontRunnerNeeded)
        {
            return "Y";
        }
        else
        {
            return null;            
        }
    }
    
    //display details
    @Override
    public String display()
    {
        String s = super.display();        
        s += "\nMembership type: " + getMemberType();        
        s += "\nDisability category: " + disabilityType;        
                
        //only display need frontrunner for visually impaired
        if (disabilityType.equalsIgnoreCase(Disabled.VISUAL_IMPAIRED))
        {
            if (isFrontRunnerNeeded)
            {
                s += "\nNeed front runner: Yes";            
            }
            else
            {
                s += "\nNeed front runner: No";             
            }
        }    
        
        return s;
    }    
}  //end class
