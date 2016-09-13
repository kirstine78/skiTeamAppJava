package dal;

/**
 *
 * @author Kirstine Nielsen. Student ID: 100527988
 * Date: 09.06.2016
 * ConnectionDetails.java
 * Version 1.0
 * Class that represents the details about the db connection
 */
public class ConnectionDetails 
{
    //class variables
    private static final String userName = "root";
    private static final String password = "root";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/ski?autoReconnect=true";

    public static String getUserName() 
    {
        return userName;
    }

    public static String getPassWord() 
    {
        return password;
    }

    public static String getDriver() 
    {
        return driver;
    }

    public static String getUrl() 
    {
        return url;
    }   
}
