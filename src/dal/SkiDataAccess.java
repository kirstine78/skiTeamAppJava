package dal;

import classes.Abled;
import classes.Disabled;
import classes.Member;
import classes.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 06.06.2016
 * SkiDataAccess.java
 * Version 1.0
 * Class for interacting with the ski database.
 */
public class SkiDataAccess 
{    
    private Connection connection = null;  //to setup a connection between program and db
    private Statement stmt = null;  //store SQL statements and execute them

    //get mysql connection details
    private String username = ConnectionDetails.getUserName();
    private String password = ConnectionDetails.getPassWord();
    private String url = ConnectionDetails.getUrl();        
        
    /**
     * Prepare a connection for database
     */
    private void prepareConnection()
    {
        try
        {            
            //load the mysql driver
            Class.forName(ConnectionDetails.getDriver());
            
            //attempt to connect
            connection = DriverManager.getConnection(url, username, password);
            
            //prepare sql statement
            stmt = connection.createStatement();   
        }
        catch (ClassNotFoundException cnfE)
        {
            System.err.println("Error connection to database");            
        }     
        catch (SQLException sqlE)
        {
            System.err.println("Error SQLException in prepareConnection()");            
        } 
    }  //end prepareConnection()    
    
    /**
     * Get max value in column member id  
     * @return    Max value of a column
     */
    public int getMaxValueMemberId()
    {
        int maxVal = 0;  //to hold the max value from column memberId
        ResultSet queryResultSet = null;  //stores the results of executed sql statements
        
        prepareConnection();  //prep connection
        
        String sql = "SELECT MAX(memberId) as myMaxId FROM member";
        
        //try to read from database
        try
        {         
            //execute query with SQL statement
            queryResultSet = stmt.executeQuery(sql);    
            
            if (queryResultSet.next())
            {
                maxVal = queryResultSet.getInt("myMaxId");
            }          
        }  
        catch (SQLException sqlE)
        {
            System.err.println("Error SQLException");            
        }
        finally
        {
            //close connection
            closeConnection();
        }
        
        return maxVal;
    }    
    
    /**
     * Insert member object details into database
     * @param m   Member to be inserted
     * @param teamId   id of the Team that member belongs to
     */
    public void insertMember(Member m, int teamId)
    {        
        prepareConnection();  //prep connection
        
        boolean isAbled = m instanceof Abled;  //set boolean      
        String sql = "INSERT INTO Member (memberId, memberType, firstName, "
                    + "lastName, streetNo, streetName, suburb, postCode, "
                    + "mobile, email, fee, totalRaces, totalWins, isFrontrunner, "
                    + "experience, disabilityType, needFrontRunner, teamId)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try
        {  
            //create prepared statement
            PreparedStatement insertRecord = connection.prepareStatement(sql);

            //fill in the prepared statement with data
            insertRecord.setInt(1, m.getMemberId());
            insertRecord.setString(2, m.getMemberType());
            insertRecord.setString(3, m.getFirstName());
            insertRecord.setString(4, m.getLastName());
            insertRecord.setString(5, m.getAddress().getStreetNo());
            insertRecord.setString(6, m.getAddress().getStreetName());
            insertRecord.setString(7, m.getAddress().getSuburb());
            insertRecord.setInt(8, m.getAddress().getPostCode());
            insertRecord.setString(9, m.getMobile());
            insertRecord.setString(10, m.getEmail());
            insertRecord.setDouble(11, m.getFee());
            insertRecord.setInt(12, m.getTotalRaces());
            insertRecord.setInt(13, m.getTotalWins());

            //if member is abled or disabled
            if (isAbled)
            {                
                Abled abled = (Abled) m;     

                insertRecord.setString(14, abled.getIsFrontRunnerDisplay());
                insertRecord.setString(15, abled.getExperience());                
                insertRecord.setString(16, null);
                insertRecord.setString(17, null);       
            }
            else  //disabled member type
            {               
                Disabled disabled = (Disabled) m;

                insertRecord.setString(14, null);
                insertRecord.setString(15, null);
                insertRecord.setString(16, disabled.getDisabilityType());
                insertRecord.setString(17, disabled.getNeedFrontrunnerDisplay());        
            }        
            insertRecord.setInt(18, teamId);        
                 
            //execute SQL statement
            insertRecord.executeUpdate();
        }   
        catch (SQLException sqlE)
        {
            System.err.println("Error SQLException in insertMember()");            
        } 
        finally
        {
            //close connection
            closeConnection();
        }
        
    }  //end insertMember()    
    
    /**
     * Update member object details in db
     * @param m   The member to update
     * @param teamId   The id for the team that member belongs to
     */
    public void updateMember(Member m, int teamId)
    {
        ArrayList <SetField> setFieldList = createSetFieldList(m, teamId);
                    
        prepareConnection();  //prepare connection
        
        String sql = "UPDATE member SET ";
                
        //build the set string            
        for (int i = 0; i < setFieldList.size(); i++)
        {
            SetField setField = setFieldList.get(i);  //fetch the setField obj

            sql += setField.getName() + "=";

            //check if value is null
            if (setField.getValue() == null)
            {
                sql += "null";
            }
            else  //there is a value
            {
                //check if single quotes are needed
                if (setField.getValue() instanceof String)
                {
                    sql += "'" + setField.getValue() + "'";
                }
                else  //a number - so no quotes
                {
                    sql += setField.getValue();
                }
            }

            if (i != setFieldList.size() - 1)
            {
                sql += ", ";
            }                
        }   
        
        sql += " WHERE memberId=" + m.getMemberId();
        
        try
        {         
            //execute SQL statement
            stmt.executeUpdate(sql);
            
        }
        catch (SQLException sqlE)
        {
            System.err.println("Error SQL Exception");            
        }
        finally
        {
            //close connection
            closeConnection();
        }
    }  //end updateMember()
    
    /**
     * Read all member object details in db
     * @param whereClauseList   ArrayList of where clauses, column name and value
     * @return   The Member objects retrieved
     */
    public ArrayList<Member> readAllMembers(ArrayList<WhereClause> whereClauseList)
    {
        prepareConnection();  //prep connection
        
        ResultSet r = null;  //stores the results of executed sql statements
        
        ArrayList<Member> memberList = new  ArrayList<>();
        
        String sql = "SELECT * FROM member";
        
        //check if there is a where clause
        if (whereClauseList != null && whereClauseList.size() > 0)
        {
            sql += " WHERE ";
            
            for (int i = 0; i < whereClauseList.size(); i++)
            {
                WhereClause w = whereClauseList.get(i);
                
                sql += w.getName() + "=";
                
                if (w.getValue() == null)
                {
                    sql += "null";
                }
                else
                {
                    if (w.getValue() instanceof String)
                    {
                        sql += "'" + w.getValue() + "'";
                    }
                    else
                    {
                        sql += w.getValue();
                    }
                }
                
                if (i != whereClauseList.size() - 1)
                {
                    sql += " AND ";
                }                
            }           
        } 
        
        //try to read from database
        try
        {            
            //execute SQL statement
            r = stmt.executeQuery(sql); 
            
            //retrieve records and place them in array list
            memberList.clear();  //remove any data from the ArrayList
            
            memberList = populateMemberList(r);                    
        }  
        catch (SQLException sqlE)
        {
            System.err.println("Error SQL Exception");            
        }
        finally
        {
            //close connection
            closeConnection();
        }
        
        return memberList;
    }  //end readAllMembers()   
    
    /**
     * Based on a result set from a query, an ArrayList will be populated with member object and returned
     * @param resultSet   The result set from a query 
     * @return   An ArrayList of member objects from the result set
     */
    private ArrayList<Member> populateMemberList(ResultSet resultSet)
    {
        ArrayList<Member> memberList = new ArrayList<>();
        
        try
        {
            //loop through the extracted records and add to ArrayList
            while (resultSet.next())
            {
                Member m;  //decl member obj
                    
                //check if record holds details about abled or disabled member
                if (resultSet.getString("memberType").equals("A"))  //abled
                {           
                    //find out if abled member is frontrunner
                    String isFrontrunnerString = resultSet.getString("isFrontrunner");
                    boolean isFrontrunner = false;
                    
                    if (isFrontrunnerString.equals("Y"))
                    {
                        isFrontrunner = true;  //update boolean
                    }
                    
                    //instantiate abled obj
                    m = new Abled(isFrontrunner, resultSet.getString("experience"), 
                                resultSet.getInt("memberId"), resultSet.getString("firstName"), 
                                resultSet.getString("lastName"), resultSet.getString("streetNo"), 
                                resultSet.getString("streetName"), resultSet.getString("suburb"), 
                                resultSet.getInt("postCode"), resultSet.getString("mobile"), 
                                resultSet.getString("email"), resultSet.getInt("totalRaces"),
                                resultSet.getInt("totalWins"));                    
                }
                else  //disabled
                {           
                    //find out if disabled member need frontrunner
                    String needFrontrunnerString = resultSet.getString("needFrontRunner");
                    boolean needFrontrunner = false;
                    
                    //if (needFrontrunnerString.equals("Y"))
                    if (needFrontrunnerString != null)
                    {
                        needFrontrunner = true;  //update boolean
                    }
                    
                    //instantiate disabled obj
                    m = new Disabled(resultSet.getString("disabilityType"), needFrontrunner, 
                                resultSet.getInt("memberId"), resultSet.getString("firstName"), 
                                resultSet.getString("lastName"), resultSet.getString("streetNo"), 
                                resultSet.getString("streetName"), resultSet.getString("suburb"), 
                                resultSet.getInt("postCode"), resultSet.getString("mobile"), 
                                resultSet.getString("email"), resultSet.getInt("totalRaces"),
                                resultSet.getInt("totalWins"));                
                }     
                
                //call method that returns a Team obj    
                Team t = readSpecificTeam(resultSet.getInt("teamId"));
                
                //set team for the member
                m.setTeam(t);
                
                //add member to the array list
                memberList.add(m);   
            }
        }  
        catch (SQLException sqlE)
        {
            System.err.println("Error SQLException");            
        }
        
        return memberList;
    }  //end populateMemberList()
    
    /**
     * Read ALL team object details in db
     * @param whereClause   String of where clause for the query
     * @return   The Team objects retrieved
     */
    public ArrayList<Team> readAllTeams(String whereClause)
    {
        prepareConnection();  //prep connection
        
        ResultSet r = null;  //stores the results of executed sql statements
        
        ArrayList<Team> teamList = new ArrayList<>();
        
        String sql = "SELECT * FROM team";
        
        //check if there is a where clause
        if (whereClause != null)
        {
            sql += " " + whereClause;
        }     
        
        //try to read from database
        try
        {            
            //execute SQL statement
            r = stmt.executeQuery(sql); 
            
            //retrieve records and place them in array list
            teamList.clear();  //remove any data from the ArrayList
            
            //loop through the extracted records and add to ArrayList
            while (r.next())
            {
                //instantiate team obj and add to arraylist
                teamList.add(new Team(r.getString("teamName"), r.getInt("teamId")));
            }          
        }  
        catch (SQLException sqlE)
        {
            System.err.println("Error SQLException");            
        }
        finally
        {
            //close connection
            closeConnection();
        }
        
        return teamList;        
    }  //end readAllTeams()
    
    /**
     * Read a Team object that has a specific teamId
     * @param teamId   id for the specific team you are reading
     * @return   A Team object that has a specific teamId
     */
    public Team readSpecificTeam(int teamId)
    {
        //read all teams and return records that apply to the where clause   
        //there should only be one record returned because teamId is primary key
        ArrayList<Team> teamList = readAllTeams("WHERE teamId=" + teamId);     
        return teamList.get(0);
    }
    
    /**
     * Delete a member in the database
     * @param memberId   id of the member to delete
     */
    public void deleteMember(int memberId)
    {
        prepareConnection();
        
        String sql = "DELETE from member WHERE memberId=" + memberId;
        
        try
        {
            stmt.executeUpdate(sql);  
        }
        catch (SQLException sqlE)
        {
            System.err.println("Error SQLException");            
        }
        finally
        {
            //close connection
            closeConnection();
        }
    }
    
    /**
     * Populate an ArrayList of SetField objects based on a certain member object and return ArrayList
     * @param m   A specific member you want to create the SetField list for
     * @param teamId   Id of the team that member belongs to
     * @return   SetField objects in an ArrayList
     */
    private ArrayList <SetField> createSetFieldList(Member m, int teamId)
    {
        ArrayList <SetField> setFieldList = new ArrayList<>();
        setFieldList.add(new SetField("memberId", m.getMemberId()));
        setFieldList.add(new SetField("memberType", m.getMemberType()));
        setFieldList.add(new SetField("firstName", m.getFirstName()));
        setFieldList.add(new SetField("lastName", m.getLastName()));
        setFieldList.add(new SetField("streetNo", m.getAddress().getStreetNo()));
        setFieldList.add(new SetField("streetName", m.getAddress().getStreetName()));
        setFieldList.add(new SetField("suburb", m.getAddress().getSuburb()));
        setFieldList.add(new SetField("postCode", m.getAddress().getPostCode()));
        setFieldList.add(new SetField("mobile", m.getMobile()));
        setFieldList.add(new SetField("email", m.getEmail()));
        setFieldList.add(new SetField("fee", m.getFee()));
        setFieldList.add(new SetField("totalRaces", m.getTotalRaces()));
        setFieldList.add(new SetField("totalWins", m.getTotalWins()));
        
        if (m instanceof Abled)
        {
            Abled a = (Abled) m;  //cast
            setFieldList.add(new SetField("isFrontrunner", a.getIsFrontRunnerDisplay()));        
            setFieldList.add(new SetField("experience", a.getExperience()));      
            setFieldList.add(new SetField("disabilityType", null));
            setFieldList.add(new SetField("needFrontRunner", null));         
        }
        else  //disabled
        {
            Disabled d = (Disabled) m;  //cast
            setFieldList.add(new SetField("isFrontrunner", null));     
            setFieldList.add(new SetField("experience", null));  
            setFieldList.add(new SetField("disabilityType", d.getDisabilityType()));
            setFieldList.add(new SetField("needFrontRunner", d.getNeedFrontrunnerDisplay()));  
            
        }
        setFieldList.add(new SetField("teamId", teamId));
        
        return setFieldList;
    }
    
    /**
     * Close database connection
     */
    private void closeConnection()
    {
        try
        {            
            connection.close();                  
        }
        catch (SQLException sqlE)
        {
            System.err.println("Connection close() failed");                  
        }        
    }
    
}  //end class
