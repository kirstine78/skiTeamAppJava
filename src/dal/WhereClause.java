package dal;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 06.06.2016
 * WhereClause.java
 * Version 1.0
 * Class to model a where clause which represents a search criteria.
 */
public class WhereClause
{
    private String name;
    private Object value;

    //constructor
    public WhereClause(String name, Object value)
    {
        this.name = name;
        this.value = value;
    }

    //getter and setter
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
}  //end class
