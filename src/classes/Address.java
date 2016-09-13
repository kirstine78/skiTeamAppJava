package classes;

import java.io.Serializable;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * Address.java
 * Version 1.0
 * Class for Address
 */
public class Address implements Serializable  //series of ones and zeros
{
    private String streetNo;
    private String streetName;
    private int postCode;
    private String suburb;

    //constructor
    public Address(String streetNo, String streetName, 
                    String suburb, int postCode) {
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.suburb = suburb;
        this.postCode = postCode;
    }

    //getter and setter
    public String getStreetNo() 
    {
        return streetNo;
    }

    public void setStreetNo(String streetNo) 
    {
        this.streetNo = streetNo;
    }

    public String getStreetName() 
    {
        return streetName;
    }

    public void setStreetName(String streetName) 
    {
        this.streetName = streetName;
    }

    public int getPostCode() 
    {
        return postCode;
    }

    public void setPostCode(int postCode) 
    {
        this.postCode = postCode;
    }

    public String getSuburb() 
    {
        return suburb;
    }

    public void setSuburb(String suburb) 
    {
        this.suburb = suburb;
    }
    
    //other method
    public String displayAddress()
    {
        return this.streetNo + " " + this.streetName + ", " + this.suburb +
                " " + this.postCode;
    }
    
}  //end class
