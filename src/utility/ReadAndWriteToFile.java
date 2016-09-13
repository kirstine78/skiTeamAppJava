package utility;

import classes.Member;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 01.06.2016
 * ReadAndWriteToFile.java
 * Version 1.0
 * Class for ReadAndWriteToFile to be used to read/write to binary file
 */
public class ReadAndWriteToFile 
{   
    private final static String fileName = "SkiMembers.bin";  //file name
    
    /**
     * Write/output Member information to file
     * @param list   All members you want to be written to file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void saveMemberFile(ArrayList<Member> list) throws FileNotFoundException, IOException 
    {                
        //make connection, pass the filename
        FileOutputStream fOut = new FileOutputStream(fileName); 
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        
        //write the whole arraylist, rather than just a single object at a time.
        oOut.writeObject(list);
        
        oOut.close();
    }  
    
    /**
     * Read/input records from binary file to memory 
     * @return   All members read from file put into an ArrayList
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static ArrayList<Member> readMemberFile() 
            throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        FileInputStream fIn = new FileInputStream(fileName);
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        
        //recreate the object. cast it to ArrayList, readObject could be any type of object
        ArrayList<Member> list = (ArrayList) oIn.readObject();
        
        oIn.close();
        
        return list;  
    }
    
}  //end class
