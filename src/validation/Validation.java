package validation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 01.06.2016
 * Validation.java
 * Version 1.0
 * Validation class to be used to validate user input.
 */
public class Validation 
{    
    private static final Color COLOR_ERROR_TEXT_FIELD = new Color(229,157,157);  //red
    private static final Color COLOR_CLEAR_TEXT_FIELD = new Color(255, 255, 255);  //white
    
    /**
     * Check if all user input is valid
     * @param someList   All input related to a Member object placed in an Array of Strings
     * @param txfList   ArrayList of JTextFields containing the text field object corresponding to the input
     * @return   True or false depending whether all input are valid
     */
    public static boolean isAllInputValid(String[] someList, ArrayList<JTextField> txfList)
    {
        boolean isValid = false;
        boolean isRaceAmountRaceWinCboValid = false;
        
        //amount of txt fields that we need to validate
        final int TXT_FIELD_AMOUNT = 10;
        
        //to hold validation return value
        boolean inputList[] = new boolean[TXT_FIELD_AMOUNT];       
        
        inputList[0] = Validation.isFirstNameValid(someList[0], txfList.get(0));
        inputList[1] = Validation.isLastNameValid(someList[1], txfList.get(1));
        inputList[2] = Validation.isStreetNumberValid(someList[2], txfList.get(2));
        inputList[3] = Validation.isStreetNameValid(someList[3], txfList.get(3));
        inputList[5] = Validation.isSuburbValid(someList[4], txfList.get(4));
        inputList[4] = Validation.isPostcodeValid(someList[5], txfList.get(5));
        inputList[6] = Validation.isMobileValid(someList[6], txfList.get(6));
        inputList[7] = Validation.isEmailValid(someList[7], txfList.get(7));
        
        //check if combo of races and wins is valid
        isRaceAmountRaceWinCboValid = Validation.isRaceAmountRaceWinCboValid(someList[8], txfList.get(8), someList[9], txfList.get(9));
        
        //add boolean to array
        if (isRaceAmountRaceWinCboValid)
        {
            inputList[8] = true;
            inputList[9] = true;            
        }
        else
        {
            inputList[8] = false;
            inputList[9] = false;            
        }
        
        //check if all inputs are valid
        for (boolean b : inputList)
        {                
            if (b)
            {
                isValid = true;
            }
            else  //b is false
            {
                isValid = false;
                break;
            }
        }
        
        return isValid;        
    }
        
    /**
     * Check if first name is valid and set text field color to white or red
     * @param name   First name of a Member object 
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether name input is a valid first name
     */
    private static boolean isFirstNameValid(String name, JTextField txf)
    {
        boolean isValid = false;
        
        final int NAME_LENGTH = name.length();
        
        //check length
        if (NAME_LENGTH > 1 && NAME_LENGTH < 21)
        {
            //loop through the string and check if the characters are all letter (a-z)
            for (int i = 0; i < NAME_LENGTH; i++)
            {
                //check for alphabet
                if (Character.isLetter(name.charAt(i)) || name.charAt(i) == ' ')
                {
                    //letter (a-z)
                    isValid = true;
                } 
                else //not letter (a-z)
                {
                    isValid = false;
                    break;                    
                }                
            }
        }
        else
        {
            isValid = false;        
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if last name is valid and set text field color to white or red
     * @param name   Last name of a Member object 
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether name input is a valid last name
     */
    private static boolean isLastNameValid(String name, JTextField txf)
    {
        boolean isValid = false;
        
        final int NAME_LENGTH = name.length();
        
        //check length
        if (NAME_LENGTH > 1 && NAME_LENGTH < 31)
        {
            //loop through the string and check if the characters are all letter (a-z)
            for (int i = 0; i < NAME_LENGTH; i++)
            {
                //check for alphabet
                if (Character.isLetter(name.charAt(i)))
                {
                    //letter (a-z)
                    isValid = true;
                } 
                else //not letter (a-z)
                {  
                    isValid = false;                    
                    break;                    
                }                
            }
        }
        else
        {
            isValid = false;        
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);        
        
        return isValid;
    }
    
    /**
     * Check if street number is valid and set text field color to white or red
     * @param str   Number of street
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether street number input is a valid
     */
    private static boolean isStreetNumberValid(String str, JTextField txf)
    {
        boolean isValid = false;
        
        if (str.length() < 1 || str.length() > 15)
        {
            isValid = false;
        }
        else  //at least length is one
        {            
            //loop through the string and check if there is at least one digit character
            for (int i = 0; i < str.length(); i++)
            {
                //if length is one and digit is 0
                if (str.length() == 1 && i == 0)
                {
                    if (str.charAt(i) == '0')
                    {
                        isValid = false; 
                        break;
                    }
                }
                //check for digit
                if (Character.isDigit(str.charAt(i)))
                {
                    //digit
                    isValid = true;           
                    break;                        
                } 
                else //not digit)
                {    
                    isValid = false;            
                }                
            }            
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if street name is valid and set text field color to white or red
     * @param str   Name of street
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether street name input is a valid
     */
    private static boolean isStreetNameValid(String str, JTextField txf)
    {
        boolean isValid = false;
        
        if (str.length() < 2 || str.length() > 30)
        {
            isValid = false; 
        }
        else
        {            
            //loop through the string and check if the characters are all letter (a-z) or space
            for (int i = 0; i < str.length(); i++)
            {
                //check for alphabet
                if (Character.isLetter(str.charAt(i)) || str.charAt(i) == ' ')
                {
                    //letter (a-z) or space
                    isValid = true;
                } 
                else //not letter (a-z)
                {    
                    isValid = false;                    
                    break;                    
                }                
            }
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if suburb is valid and set text field color to white or red
     * @param str   Name of suburb
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether suburb input is a valid
     */
    private static boolean isSuburbValid(String str, JTextField txf)
    {
        boolean isValid = false;
        
        if (str.length() < 2 || str.length() > 30)
        {
            isValid = false; 
        }
        else
        {                     
            //loop through the string and check if the characters are all letter (a-z) or space
            for (int i = 0; i < str.length(); i++)
            {
                //check for alphabet
                if (Character.isLetter(str.charAt(i)) || str.charAt(i) == ' ')
                {
                    //letter (a-z) or space
                    isValid = true;
                } 
                else //not letter (a-z)
                {    
                    isValid = false;                    
                    break;                    
                }                
            }
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if postcode is valid and set text field color to white or red
     * @param str   Postcode
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether postcode input is a valid
     */
    private static boolean isPostcodeValid(String str, JTextField txf)
    {
        boolean isValid = false;
        
        if (str.length() != 4)
        {
            isValid = false;
        }
        else  //length 4
        {              
            //loop through the string and check if the characters are all digits
            for (int i = 0; i < str.length(); i++)
            {                
                //check for digit
                if (Character.isDigit(str.charAt(i)))
                {
                    //check for index zero, cannot be 0
                    if (i == 0 && str.charAt(i) == '0')
                    {
                        isValid = false;   
                        break;                            
                    }
                    else
                    {
                        isValid = true;                       
                    }
                } 
                else //not digit
                {   
                    isValid = false;   
                    break;                    
                }                
            }
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if mobile is valid and set text field color to white or red
     * @param str   Mobile number
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether mobile input is a valid
     */
    private static boolean isMobileValid(String str, JTextField txf)
    {
        boolean isValid = false;
                
        //check length
        if (str.length() == 10)
        {
            //loop through the string and check if the characters are all digits
            for (int i = 0; i < str.length(); i++)
            {
                //check for digits
                if (Character.isDigit(str.charAt(i)))
                {
                    //digit
                    isValid = true;
                } 
                else //not digit
                {
                    isValid = false;
                    break;                    
                }                
            }            
        }
        else
        {
            isValid = false;        
        }
            
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Found email validation here: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
     * Check if email is valid and set text field color to white or red
     * @param str   email
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether email input is a valid
     */
    private static boolean isEmailValid(String str, JTextField txf)
    {
        boolean isValid = false;        
        
        Pattern pattern;
	Matcher matcher;

	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        pattern = Pattern.compile(EMAIL_PATTERN);
        
        matcher = pattern.matcher(str);        
        
	isValid = matcher.matches();
        
        //check length
        if (str.length() > 30)
        {
            isValid = false;
        }
            
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }    
    
    /**
     * Check if race amount and race wins combination is valid and set text field color to white or red
     * @param races   Number of races done
     * @param txfRaces   The text field corresponding to the input for races done
     * @param wins   Number of wins
     * @param txfWins   The text field corresponding to the input for wins
     * @return   True or false whether race >=  win
     */
    private static boolean isRaceAmountRaceWinCboValid(String races, JTextField txfRaces, String wins, JTextField txfWins)
    {
        boolean isValid = false;
        
        boolean isRaceOk = Validation.isIntegerValid(races, txfRaces);
        boolean isWinOk = Validation.isIntegerValid(wins, txfWins);
        
        //both has to be valid input
        if (isRaceOk && isWinOk) 
        {
            //races must be equal or more than wins
            int r = Integer.parseInt(races);
            int w = Integer.parseInt(wins);
            if (r >= w)
            {
                isValid = true;
            } 
            else  //cbo not ok
            {
                //both valid integers but wins > races
                Validation.setTxfBackgroundColor(txfWins, isValid);
            }
        }
        
        return isValid;        
    }
    
    /**
     * Check if input is integer (0-99999) and set text field color to white or red
     * @param str   Integer
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether input is 0-99999
     */
    private static boolean isIntegerValid(String str, JTextField txf)
    {
        boolean isValid = false;
        
        if (str.length() > 0)
        {            
            //loop through the string and check if the characters are all digits
            for (int i = 0; i < str.length(); i++)
            {
                //check for digits
                if (Character.isDigit(str.charAt(i)))
                {
                    //digit
                    isValid = true;
                } 
                else //not digit
                {
                    isValid = false;
                    break;                    
                }                
            }
            
            if (isValid && str.length() > 1)
            {
                //if length more than one then it can't be zero at index zero
                if (str.charAt(0) == '0')
                {
                    isValid = false;
                }                
            }
        }
        
        //check length
        if (str.length() > 5)
        {
            isValid = false;
        }
        
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if member id is valid and set text field color to white or red
     * @param str   Member id
     * @param txf   The text field corresponding to the input
     * @return   True or false based on whether member id is integer
     */
    public static boolean isMemberIdValid(String str, JTextField txf)
    {        
        boolean isValid = false;
        
        if (str.length() > 0)
        {           
            //loop through the string and check if the characters are all digits
            for (int i = 0; i < str.length(); i++)
            {                
                //check for digit
                if (Character.isDigit(str.charAt(i)))
                {
                    //check for index zero, cannot be 0
                    if (i == 0 && str.charAt(i) == '0')
                    {
                        isValid = false;   
                        break;                            
                    }
                    else
                    {
                        isValid = true;                       
                    }
                } 
                else //not digit
                {   
                    isValid = false;   
                    break;                    
                }                
            }       
        }
        
        //set color
        Validation.setTxfBackgroundColor(txf, isValid);
        
        return isValid;
    }
    
    /**
     * Check if a string is empty
     * @param str   Any string
     * @return   True or false depending on length of string
     */
    public static boolean isStrEmpty(String str)
    {        
        boolean isEmpty = false;
        
        if (str.length() == 0)
        {
            isEmpty = true;
        }       
        
        return isEmpty;        
    }
    
    /**
     * Set background color of a text field
     * @param txf   Any text field
     * @param isOk   Boolean indicating whether to set background color to white or red
     */
    private static void setTxfBackgroundColor(JTextField txf, boolean isOk)
    {
        //set color
        if(isOk)
        {
            //show ok color
            txf.setBackground(COLOR_CLEAR_TEXT_FIELD);
        }
        else
        {
            //show error color
            txf.setBackground(COLOR_ERROR_TEXT_FIELD);
        }        
    }
    
}  //end Validation class
