package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 19.06.2016
 * AboutFrame.java
 * Version 1.0
 * AboutFrame shows details about the Ski Member Application
 */
public class AboutFrame extends JFrame implements ActionListener, WindowListener
{
    private final int MARGIN_HORIZONTAL = 20;
    private final int MARGIN_VERTICAL = 10;
    
    //declare and create
    private JLabel lblTitle = new JLabel("SkiMemberApplication Version 1.0");
    private JLabel lblAuthor = new JLabel("Author: Kirstine B. Nielsen");    
    private JLabel lblLicence = new JLabel("<html>Licence:<br><br>This program is free "
                                    + "software.<br><br>This program is "
                                    + "developed in the hope<br>that it will "
                                    + "be useful for managing members<br>in "
                                    + "Australian Ski Clubs.</html>");
    
    private MyButton btnOk = new MyButton("OK", 12, 16);
    
    //declare container obj and panel obj
    private Container content;
    private JPanel panelNorth;
    private JPanel panelCenter;
    private JPanel panelSouth;
    
    private MainMenuFrame mainMenuFrame;
    
    public AboutFrame(MainMenuFrame m)
    {
        mainMenuFrame = m;  //assign the MainMenuFrame obj to mainMenuFrame
        
        //set title, size, location, visibility, close and resizability
        this.setTitle("About");
        this.setSize(300, 320);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //what to do on close   
        this.setResizable(false);  //constant size   
        
        panelNorth = new JPanel();  //flow by default
        panelCenter = new JPanel();  //flow by default
        panelSouth = new JPanel();  //flow by default
        
        //get the content pane
        content = getContentPane();
        
        //add panels
        content.add(panelNorth, BorderLayout.NORTH);
        content.add(panelCenter, BorderLayout.CENTER);
        content.add(panelSouth, BorderLayout.SOUTH);
        
        //set layout
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
        
        //margin (top, left, bottom, right)
        Border borderLblTitle = lblTitle.getBorder();
        Border margin1 = new EmptyBorder(MARGIN_VERTICAL, 0, MARGIN_VERTICAL, 0);
        lblTitle.setBorder(new CompoundBorder(borderLblTitle, margin1));
        
        Border borderLblAuthor = lblAuthor.getBorder();
        Border margin2 = new EmptyBorder(MARGIN_VERTICAL, 0, MARGIN_VERTICAL, 0);
        lblAuthor.setBorder(new CompoundBorder(borderLblAuthor, margin2));
        
        //set border to give a margin (top, left, bottom, right)
        panelNorth.setBorder(new EmptyBorder(MARGIN_VERTICAL, MARGIN_HORIZONTAL, MARGIN_VERTICAL, MARGIN_HORIZONTAL));        
        panelCenter.setBorder(new EmptyBorder(MARGIN_VERTICAL, MARGIN_HORIZONTAL, MARGIN_VERTICAL, MARGIN_HORIZONTAL));
        
        //add components
        panelNorth.add(lblTitle);
        panelNorth.add(lblAuthor);
        panelCenter.add(lblLicence);
        panelSouth.add(btnOk);
        
        //add action listener
        btnOk.addActionListener(this);
        
        //add windowListener to the frame
        this.addWindowListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //when ok button is clicked
        closeAbout();
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
        closeAbout();
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
    
    //dispose about frame and enable the main menu frame 
    private void closeAbout()
    {
        mainMenuFrame.setEnabled(true);
        this.dispose();      
    }
}  //end class
