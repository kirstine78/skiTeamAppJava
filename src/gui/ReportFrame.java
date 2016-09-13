package gui;

import classes.Member;
import classes.MemberTableModel;
import dal.SkiDataAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 12.06.2016
 * ReportFrame.java
 * Version 1.0
 * Class for ReportFrame to model the frame showing report of all members
 */
public class ReportFrame extends JFrame implements ActionListener, WindowListener
{    
    //declare array list to hold Member objects
    private ArrayList<Member> memberList;    
        
    //table to display table layout
    private JTable tblMember;
    private MemberTableModel memberTblModel;  //decl MemberTableModel obj
    private JScrollPane scroll;
    
    //create components
    private JLabel lblHeadline = new JLabel("Report");      
    private MyButton btnBack = new MyButton("Back", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);   
    private MyButton btnSortLastName = new MyButton("Sort by last name", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnSortType = new MyButton("Sort by type", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);    
    
    //create a font
    private Font fHeadline = new Font("Trebuchet MS", Font.PLAIN, 30);
    
    //get the content pane
    private Container content = getContentPane();
    
    //create panel
    private JPanel panelHeadline = new JPanel();
    private JPanel panelCenter = new JPanel(new BorderLayout());
    
    //create sub panels
    private JPanel panelButtons = new JPanel(new GridLayout(1, 3));   
    
    //declare MainMenuFrame object
    private MainMenuFrame mainMenuObj;
    
    /**
     * Constructor for ReportFrame
     * @param frameObj   Reference to the main menu frame where the constructor is called from
     */
    public ReportFrame(MainMenuFrame frameObj)
    {
        //assign frameObj to obj
        mainMenuObj = frameObj;
        
        //get all members in db presented in array list
        memberList = getMemberArrayList();
                
        //set title, size, location, visibility, close and resizability
        this.setTitle("Report");
        this.setSize(MainMenuFrame.FRAME_WIDTH, MainMenuFrame.FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //what to do on close  
        this.setVisible(true);  
        this.setResizable(false);  //constant size  
        
        //add panels
        content.add(panelHeadline, BorderLayout.NORTH);
        content.add(panelCenter, BorderLayout.CENTER);
            
        tblMember = new JTable();  //create table        
        tblMember.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //make sure table doesn't try to squeze columns        
        memberTblModel = new MemberTableModel(memberList);  //create member table model
        tblMember.setModel(memberTblModel);  //set model for the table        
        
        //loop through columns of tblMember
        for (int i = 0; i < memberTblModel.columnWidths.length; i++)
        {
            //set the width of each column
            tblMember.getColumnModel().getColumn(i).setPreferredWidth(memberTblModel.columnWidths[i]);
        }
        
        //add tblMember to the scroll pane constructor, and set scrollbar as needed
        scroll = new JScrollPane(tblMember, 
                                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //add components to panelCenter
        panelCenter.add(scroll, BorderLayout.CENTER);
        panelCenter.add(panelButtons, BorderLayout.SOUTH);
                
        //add components to panels
        panelHeadline.add(lblHeadline);
        panelButtons.add(btnSortLastName);
        panelButtons.add(btnSortType);
        panelButtons.add(btnBack);
        
        //add features to headline related
        panelHeadline.setBackground(new Color(70,130,180));
        panelHeadline.setBorder(new EmptyBorder(10, 5, 10, 5)); //margin
        lblHeadline.setFont(fHeadline);
        lblHeadline.setForeground(Color.white);     
        
        btnBack.addActionListener(this);
        btnSortLastName.addActionListener(this);
        btnSortType.addActionListener(this);
        
        //add windowListener to the frame
        this.addWindowListener(this);
    }    
    
    /**
     * Get all members from database presented in an ArrayList
     * @return   All member objects from database presented in an ArrayList
     */
    private ArrayList<Member> getMemberArrayList()
    {    
        //create ski data access obj
        SkiDataAccess sda = new SkiDataAccess();
        
        return sda.readAllMembers(null);
    }
    
    /**
     * Sort all member objects by last name in ascending order
     */
    private void sortByLastName()
    {        
        int a;
        int b;
        Member m;
        String str1ToUpper;
        String str2ToUpper;
        
        //sorting ascending
        for (a = 0; a < memberList.size() - 1; a++)
        {
            for (b = 0; b < memberList.size() - 1; b++)
            {   
                str1ToUpper = memberList.get(b).getLastName().toUpperCase();
                str2ToUpper = memberList.get(b + 1).getLastName().toUpperCase();
                
                if (str1ToUpper.compareTo(str2ToUpper) > 0)
                {
                    //do swapping process
                    m = memberList.get(b);  //put member object in temporary
                    memberList.set(b, memberList.get(b + 1));                    
                    memberList.set((b + 1), m);
                }
            }
        }
    }
    
    /**
     * Sort all member objects by member type in ascending order
     */
    private void sortByMemberType()
    {        
        int a;
        int b;
        Member m;
        String str1;
        String str2;
        
        //sorting ascending
        for (a = 0; a < memberList.size() - 1; a++)
        {
            for (b = 0; b < memberList.size() - 1; b++)
            {   
                str1 = memberList.get(b).getMemberType();
                str2 = memberList.get(b + 1).getMemberType();
                
                if (str1.compareTo(str2) > 0)
                {
                    //do swapping process
                    m = memberList.get(b);  //put member object in temporary
                    memberList.set(b, memberList.get(b + 1));                    
                    memberList.set((b + 1), m);
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnBack)
        {
            this.setVisible(false);
            mainMenuObj.setVisible(true);            
        } 
        if (e.getSource() == btnSortLastName)
        {
            sortByLastName();
            memberTblModel.fireTableDataChanged();  //fire to refesh          
        }  
        if (e.getSource() == btnSortType)
        {
            sortByMemberType();
            memberTblModel.fireTableDataChanged();  //fire to refesh          
        } 
    }
    
    @Override
    public void windowActivated(WindowEvent e)
    {
        
    }
    
    @Override    
    public void windowClosed(WindowEvent e)
    {
        
    }
    
    @Override 
    public void windowClosing(WindowEvent e)
    {
        mainMenuObj.setVisible(true);
        this.setVisible(false);        
    }
    
    @Override 
    public void windowDeactivated(WindowEvent e)
    {
        
    }
    
    @Override 
    public void windowDeiconified(WindowEvent e)
    {
        
    }
    
    @Override 
    public void windowIconified(WindowEvent e)
    {
        
    }
    
    @Override 
    public void windowOpened(WindowEvent e)
    {
        
    }    
    
}  //end class
