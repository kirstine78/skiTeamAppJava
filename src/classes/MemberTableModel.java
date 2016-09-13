package classes;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 12.06.2016
 * MemberTableModel.java
 * Version 1.0
 * Class that holds the members for display in a table
 */
public class MemberTableModel extends AbstractTableModel
{
    //data extracted from the db will be created into
    //Member objects and added to the Array list
    private ArrayList<Member> memberList;
    
    //assign column names to string array
    private String[] columnNames = {"Id", "Type", "First name", "Last name", 
        "Street no", "Street name", "Suburb", "Postcode", "Mobile", "Email", 
        "Fee $", "Races", "Wins", "Win %", "Is a Frontrunner?", "Experience", 
        "Disability", "Need Frontrunner?", "Team"};
    
    public int[] columnWidths = {40, 40, 130, 130, 60, 150, 100, 70, 100, 250, 50, 50, 50, 50, 100, 100, 100, 120, 160};
        
    //constructor takes array list of members as arg
    public MemberTableModel(ArrayList<Member> memberList)
    {
        this.memberList = memberList;
    }
    
    /*
    At a minimum this class must implement the 3 abstract methods inherited from
    the abstract class: AbstractTableModel
      - public int getRowCount();
      - public int getColumnCount();
      - public Object getValueAt(int row, int column);    
    */
    @Override
    public int getRowCount()
    {
        return memberList.size();  //how many rows in the table
    }
    
    @Override
    public int getColumnCount()
    {
        return columnNames.length;  //how many columns to display in the table
    }
    
    @Override
    public Object getValueAt(int row, int col)
    {
        //assign Member obj from arraylist to member
        Member member = memberList.get(row);
                
        boolean isAbled = member instanceof Abled;// check if type is abled
        
        switch(col)
        {
            case 0:
                return member.getMemberId();
            case 1:
                return member.getMemberType();
            case 2:
                return member.getFirstName();
            case 3:
                return member.getLastName();
            case 4:
                return member.getAddress().getStreetNo();
            case 5:
                return member.getAddress().getStreetName();
            case 6:
                return member.getAddress().getSuburb();
            case 7:
                return member.getAddress().getPostCode();
            case 8:
                return member.getMobile();
            case 9:
                return member.getEmail();
            case 10:
                return member.getFee();
            case 11:
                return member.getTotalRaces();
            case 12:
                return member.getTotalWins();
            case 13:
                return member.calcWinPercentage();
            case 14:
                if (isAbled)
                {
                    Abled a = (Abled) member;
                    return a.getIsFrontRunnerDisplay();                    
                }
                else
                {
                    return "";
                }
            case 15:
                if (isAbled)
                {
                    Abled a = (Abled) member;
                    
                    if (a.getExperience() != null)  //frontrunner
                    {
                        return a.getExperience();
                    }
                    else  //not frontrunner
                    {
                        return "";
                    }
                }
                else  //disabled
                {
                    return "";
                }
            case 16:
                if (isAbled)
                {
                    return "";
                }
                else  //disabled
                {
                    Disabled d = (Disabled) member;
                    return d.getDisabilityType();
                }
            case 17:
                if (isAbled)
                {
                    return "";
                }
                else  //disabled
                {
                    Disabled d = (Disabled) member;
                    if (d.getIsFrontRunnerNeeded())
                    {
                        return d.getNeedFrontrunnerDisplay();
                    }
                    else
                    {
                        return "";
                    }
                }
            case 18:
                return member.getTeam().getTeamName();            
        }
        
        return null;
    }
    
    /*
    Non-abstract method inherited from AbstractTableModel -
    this method already has a method body but can still be 
    overridden by creating a new version of this method
    */    
    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];  //from {"Id", "Type", "First name", "Last name", .....}
    }
    
    //This method will extract one obj (record) from the ArrayList at the specified row
    public Member getRow(int row)
    {
        Member m = memberList.get(row);  //assign member obj from arraylist to m
        return m;
    }
    
}  //end class
