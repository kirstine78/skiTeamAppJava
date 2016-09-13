package dal;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 06.06.2016
 * SetField.java
 * Version 1.0
 * Class to store a field/column name and an associated update value. 
 */
public class SetField
{
    private String fldName;
    private Object fldValue;

    public SetField(String name, Object value)
    {
        this.fldName = name;
        this.fldValue = value;
    }

    public String getName()
    {
        return fldName;
    }

    public void setName(String name)
    {
        this.fldName = name;
    }

    public Object getValue()
    {
        return fldValue;
    }

    public void setValue(Object value)
    {
        this.fldValue = value;
    }
}  //end class
