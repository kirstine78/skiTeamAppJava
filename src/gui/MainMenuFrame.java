package gui;

import classes.Abled;
import classes.Disabled;
import classes.Member;
import dal.SkiDataAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import utility.ReadAndWriteToFile;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * MainMenuFrame.java
 * Version 1.0
 * Class for MainMenuFrame, modeling GUI for the main menu
 */
public class MainMenuFrame extends JFrame implements ActionListener
{
    public static final int FRAME_WIDTH = 1300;
    public static final int FRAME_HEIGHT = 790;
    
    public static final int FONT_SIZE = 26;
    public static final int FONT_SIZE_HOVER = 30;
    
    //create label
    private JLabel lblHeadline = new JLabel("Australian Ski Association");    
    
    //create buttons
    private MyButton btnAddMember = new MyButton("Add new Member", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnSearchMember = new MyButton("Search a Member", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnReport = new MyButton("Show Report", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnExit = new MyButton("Exit", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);    
    
    //declare menu items
    private JMenuItem mitAddMember;
    private JMenuItem mitSearchMember;
    private JMenuItem mitReport;
    private JMenuItem mitBackup;
    private JMenuItem mitExit;
    private JMenuItem mitSkiAppHelp;
    private JMenuItem mitAbout;
    
    //create image object
    private ImageIcon img1;
    private JLabel lblImage;
    
    //create font object
    private Font fHeadline = new Font("Trebuchet MS", Font.PLAIN, 80);
    
    //get the content pane
    private Container content = getContentPane();
    
    //create panel
    private JPanel panelHeadline = new JPanel();
    private JPanel panelCenter = new JPanel(new BorderLayout());
    
    //create sub panel
    private JPanel panelCenterImage = new JPanel();
    private JPanel panelCenterButtons = new JPanel(new GridLayout(2, 2));
    
    //constructor
    public MainMenuFrame()
    {
        //set title, size, location, visibility, close and resizability
        super("Australian Ski Association");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);  //size of frame
        this.setLocationRelativeTo(null);  //place frame in center of screen        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
		
        //create a menu bar obj
        buildMenuSystem();
                
        //add panel to content
        content.add(panelHeadline, BorderLayout.NORTH);
        content.add(panelCenter, BorderLayout.CENTER);
                
        //add sub panels to panelCenter
        panelCenter.add(panelCenterImage, BorderLayout.NORTH);
        panelCenter.add(panelCenterButtons, BorderLayout.CENTER);
        
        //organize headline related
        panelHeadline.setBackground(new Color(70,130,180));
        lblHeadline.setForeground(Color.white);
        //set border to give a margin (top, left, bottom, right)
        panelHeadline.setBorder(new EmptyBorder(10, 5, 10, 5));
        
        //instantiate image
        img1 = new ImageIcon("images/logo1300.jpg");
        lblImage = new JLabel(img1); //instantiate label and add image        
        
        //add headline and label with image to panel
        panelHeadline.add(lblHeadline);	
        panelCenterImage.add(lblImage);	
        
        //set font for the original greeting
        lblHeadline.setFont(fHeadline);
        
        //add button to the panelCenterCenter grid       
        panelCenterButtons.add(btnAddMember);
        panelCenterButtons.add(btnSearchMember);
        panelCenterButtons.add(btnReport);
        panelCenterButtons.add(btnExit);   
        
        //register the MainMenuFrame object as the listener for the JButton and menuItem obj
        btnAddMember.addActionListener(this); 
        btnSearchMember.addActionListener(this);
        btnReport.addActionListener(this);
        btnExit.addActionListener(this);
        
        mitAddMember.addActionListener(this);
        mitSearchMember.addActionListener(this);
        mitReport.addActionListener(this);
        mitBackup.addActionListener(this);
        mitExit.addActionListener(this);
        mitAbout.addActionListener(this);
        mitSkiAppHelp.addActionListener(this);
        
        //add windowListener to the frame
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                exitApp();                
            }
        });
        
    }
    
    /**
     * Build the menu bar at the top of the frame
     * @return   The menu bar for the frame
     */
    private JMenuBar buildMenuSystem()
    {
        //create a menu bar obj
        JMenuBar menuBar = new JMenuBar();

        //Set menu bar to the frame
        this.setJMenuBar(menuBar);
	    
        //instantiate menu obj
        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");

        //set mnemonics for menu obj
        menuFile.setMnemonic('F');
        menuHelp.setMnemonic('H');
		
        //add menu items with mnemonics 
        menuFile.add(mitAddMember = new JMenuItem("Add new Member", 'M'));
        menuFile.add(mitSearchMember = new JMenuItem("Search a Member", 'S'));
        menuFile.add(mitReport = new JMenuItem("Show Report", 'R'));
        menuFile.add(mitBackup = new JMenuItem("Backup to File", 'B'));
        menuFile.add(mitExit = new JMenuItem("Exit", 'E'));
        menuHelp.add(mitSkiAppHelp = new JMenuItem("Ski App Help", ' '));
        menuHelp.add(mitAbout = new JMenuItem("About", 'A'));

        //set keyboard accelerators (keyboard shortcuts)
        mitAddMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        mitSearchMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        mitReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        mitBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        mitExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        mitSkiAppHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        mitAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));        

        //add menu obj to menu bar
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        
        return menuBar;
    }
    
    //listener method for the ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e)
    {        
        //check which button or menu item was clicked
        if (e.getSource() == btnAddMember || e.getSource() == mitAddMember)
        {                    
            //create MemberFrame object. pass ref for this MainMenuFrame obj and arraylist
            MemberFrame addMember = new MemberFrame(this, "Add Member", "Enter Member Details", null);

            //set current object to invisible (current is the main frame) 
            this.setVisible(false);             
        }  
        //check which button or menu item was clicked
        if (e.getSource() == btnSearchMember || e.getSource() == mitSearchMember)
        {
            //create SearchMemberFrame object. pass ref for this MainMenuFrame object to constructor
            SearchMemberFrame searchMember = new SearchMemberFrame(this);

            //set current object to invisible (current is the main frame) 
            this.setVisible(false);  
        }   
        //check which button or menu item was clicked
        if (e.getSource() == btnReport || e.getSource() == mitReport)
        {
            //create SearchMemberFrame object. pass ref for this MainMenuFrame object to constructor
            ReportFrame report = new ReportFrame(this);

            //set current object to invisible (current is the main frame) 
            this.setVisible(false);  
        }    
        //check which menu item was clicked
        if (e.getSource() == mitBackup)
        {
            backupToBinaryFile();            
        }   
        //check which menu item  was clicked
        if (e.getSource() == mitSkiAppHelp)
        {             
            //code found on: http://stackoverflow.com/questions/2546968/open-pdf-file-on-the-fly-from-a-java-application
            if (Desktop.isDesktopSupported()) 
            {
                try 
                {
                    File myFile = new File("userDocs/skiAppUserDocs.pdf");
                    
                    //open the pdf file with the user documentation
                    Desktop.getDesktop().open(myFile);
                } 
                catch (IOException ex) 
                {
                    //no application registered for PDFs
                    JOptionPane.showMessageDialog(null, "Sorry you need to install a Pdf reader");
                }
            }
        }       
        //check which menu item  was clicked
        if (e.getSource() == mitAbout)
        { 
            AboutFrame aboutFrame = new AboutFrame(this);
            this.setEnabled(false);
        }       
        //check which button was clicked
        if (e.getSource() == btnExit || e.getSource() == mitExit)
        {            
            exitApp();
        }           
    }   
    
    /**
     * Write all member objects to a binary file and then exit application
     */
    private void exitApp()
    {        
        backupToBinaryFile();
        //exit
        System.exit(0);
    }
    
    /**
     * Write all member objects to a binary file
     */
    private void backupToBinaryFile()
    {
        //create ski data access obj
        SkiDataAccess sda = new SkiDataAccess();
        
        //get all members from db
        ArrayList<Member> memberList = sda.readAllMembers(null);
        
        try
        {
            //write to file
            ReadAndWriteToFile.saveMemberFile(memberList);
            JOptionPane.showMessageDialog(null, "Data successfully saved to \"SkiMembers.bin\""); 
        }
        catch (FileNotFoundException e)
        {
            //display error message to user
            JOptionPane.showMessageDialog(null, "Cannot save data to file."
                                                + "\nFile not found");                  
        }
        catch (IOException ex)  //more generic
        {
            //display error message to user
            JOptionPane.showMessageDialog(null, "Cannot save data to file."
                                                + "\nError writing to file");                       
        } 
    }
    
    //NOT used in app (tester to see if read from file works)
    private void displayMembersFromFile()
    {
        try
        {
            String s = "";
            ArrayList<Member> memberList = ReadAndWriteToFile.readMemberFile();
            
            for (Member m : memberList)
            {
                if (m instanceof Abled)
                {
                    //cast
                    Abled a = (Abled) m;
                    s += "\n\n" + a.display();
                }
                else  //disabled
                {
                    //cast
                    Disabled d = (Disabled) m;
                    s += "\n\n" + d.display();                    
                }
            }
            System.out.println(s);
        }
        catch (IOException ex)  //more generic
        {
            //display error message to user
            JOptionPane.showMessageDialog(null, "Error reading from file"
                                            + "\nProgram will terminate"); 
        }
        catch (ClassNotFoundException ex) 
        {
            //display error message to user
            JOptionPane.showMessageDialog(null, "Error class not found"
                                            + "\nProgram will terminate");
        }        
    }  //end displayMembersFromFile()
    
}  //end class