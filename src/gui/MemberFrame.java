package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import classes.Member;
import dal.SkiDataAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * MemberFrame.java
 * Version 1.0
 * Class for MemberFrame, modeling GUI for Adding and Editing a member
 */
public class MemberFrame extends JFrame implements ActionListener, WindowListener
{    
    //declare objects gui
    private MyButton btnAdd = new MyButton("Add", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnEdit = new MyButton("Save", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnClear = new MyButton("Clear", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnBack = new MyButton("Back", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER); 
    
    private JLabel lblHeadline = new JLabel();    
    private Font f1Headline = new Font("Trebuchet MS", Font.BOLD, 30);    
    
    //get the content pane
    private Container content = getContentPane();  //borderlayout
    
    //create panels
    private JPanel panelHeadline = new JPanel();  //flowlayout
    private JPanel panelMainContent = new JPanel(new BorderLayout());  //flowlayout
    
    //create sub panels
    private MemberPanel panelMember;
    private JPanel panelButtons = new JPanel(new GridLayout(1, 3));  //for bottom buttons   
    
    //declare JFrame object
    private JFrame parentFrame;
    
    //constructor
    public MemberFrame(JFrame frame, String title, String labelHeadline, Member m)
    {        
        parentFrame = frame; //assign menu to obj
        
        //set title, size, location, visibility, close and resizability
        this.setTitle(title);       
        this.setSize(MainMenuFrame.FRAME_WIDTH, MainMenuFrame.FRAME_HEIGHT);  //set size
        this.setLocationRelativeTo(null);  //place frame in center of screen           
        this.setVisible(true);  //visible
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //what to do on close      
        this.setResizable(false);  //constant size    
        
        //initialize the MEMBER PANEL object
        panelMember = new MemberPanel(this, m);
        //set border to give a margin (top, left, bottom, right)
        panelMember.setBorder(new EmptyBorder(10, 300, 10, 300));  
                
        //add panel to content
        content.add(panelHeadline, BorderLayout.NORTH);
        content.add(panelMainContent, BorderLayout.CENTER);
        
        //add sub panels to panelMainContent
        panelMainContent.add(panelMember, BorderLayout.NORTH);
        panelMainContent.add(panelButtons, BorderLayout.SOUTH);
        
        //do headline related
        lblHeadline.setText(labelHeadline);
        //set border to give a margin (top, left, bottom, right)
        panelHeadline.setBorder(new EmptyBorder(10, 5, 10, 5));
        //color for headline
        panelHeadline.setBackground(new Color(70,130,180));
        lblHeadline.setForeground(new Color(255, 255, 255));
        
        //set font for labels
        lblHeadline.setFont(f1Headline); 
                
        //add labels and textfields
        panelHeadline.add(lblHeadline); 
        
        //add buttons
        if (m == null)  //add frame
        {
            panelButtons.add(btnAdd);
        }
        else  //edit frame
        {
            panelButtons.add(btnEdit);
        }
        panelButtons.add(btnClear);
        panelButtons.add(btnBack);    
        
        //register action listener
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnClear.addActionListener(this);
        btnBack.addActionListener(this);    
        
        //add windowListener to the frame
        this.addWindowListener(this);
    }
    
    //method to respond to mouse click
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnAdd)
        {   
            doAddMember(); 
        }
        if (e.getSource() == btnEdit)
        {   
            doEditMember();
        }
        if (e.getSource() == btnClear)
        {
            panelMember.clearForm();
        }
        if (e.getSource() == btnBack)
        {
            this.setVisible(false);  //still in memory            
            parentFrame.setVisible(true);            
        }
    }
    
    /**
     * Add member object to database
     */
    private void doAddMember()
    {
        Member member;

        //if all valid 
        if (panelMember.isAllInputValid())
        {                             
            member = panelMember.createMemberBasedOnTxfData(); 

            int intTeamId = panelMember.getSelecetedTeamId();

            //create skidataaccess obj
            SkiDataAccess skiDataAccess = new SkiDataAccess();

            //try to insert member to db
            skiDataAccess.insertMember(member, intTeamId); 
                        
            //hide this frame
            this.setVisible(false);  //still in memory
            parentFrame.setVisible(true); //show main menu 
        }     
    }
    
    /**
     * Edit a member object's details
     */
    private void doEditMember()
    {
        Member member;

        //if all valid 
        if (panelMember.isAllInputValid())
        {                             
            member = panelMember.createMemberBasedOnTxfData(); 

            int intTeamId = panelMember.getSelecetedTeamId();

            //create skidataaccess obj
            SkiDataAccess skiDataAccess = new SkiDataAccess();

            //try to insert member to db
            skiDataAccess.updateMember(member, intTeamId);
            
            //hide this frame
            this.setVisible(false);  //still in memory
            
            //refresh before setting visible
            if (parentFrame instanceof SearchMemberFrame)
            {
                SearchMemberFrame searchFrame = (SearchMemberFrame) parentFrame;
            
                searchFrame.doSearchMember(true);                
            }
            
            //set visible
            parentFrame.setVisible(true); //show main menu  
        }        
    }  //end editMember()
    
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
        parentFrame.setVisible(true);
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
