package gui;

import classes.Member;
import classes.MemberTableModel;
import dal.SkiDataAccess;
import dal.WhereClause;
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
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import validation.Validation;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 06.06.2016
 * SearchMemberFrame.java
 * Version 1.0
 * Class for SearchMemberFrame
 */
public class SearchMemberFrame extends JFrame implements ActionListener, WindowListener
{    
    private int selectedRow = -1;  //which row selected/highlighted by user
    
    //declare array list to hold Member objects
    private ArrayList<Member> memberList;  
        
    //table to display table layout
    private JTable tblMember;
    private MemberTableModel memberTblModel;  //decl MemberTableModel obj
    private JScrollPane scroll;
    
    //create components
    private JLabel lblHeadline = new JLabel("Search Member (enter optional search criteria)");     
    private JLabel lblId = new JLabel("Member Id");
    private JLabel lblType = new JLabel("Membership type");
    private JLabel lblFirstName = new JLabel("First name");
    private JLabel lblLastName = new JLabel("Last name");
    
    private JTextField txfId = new JTextField(10);
    private JTextField txfFirstName = new JTextField(10);
    private JTextField txfLastName = new JTextField(10);  
    
    private JRadioButton radTypeUnknown = new JRadioButton("Unknown", true);
    private JRadioButton radTypeAbled = new JRadioButton("Abled");
    private JRadioButton radTypeDisabled = new JRadioButton("Disabled");
    
    //declare ButtonGroup for radio buttons.
    private ButtonGroup typeGroup = new ButtonGroup();
    
    private MyButton btnSearch = new MyButton("Search", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnEdit = new MyButton("Edit", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnDelete = new MyButton("Delete", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    private MyButton btnBack = new MyButton("Back", MainMenuFrame.FONT_SIZE, MainMenuFrame.FONT_SIZE_HOVER);
    
    private Font fHeadline = new Font("Trebuchet MS", Font.PLAIN, 30);
    
    //get the content pane
    private Container content = getContentPane();
    
    //create panel
    private JPanel panelHeadline = new JPanel();
    private JPanel panelCenter = new JPanel(new BorderLayout());
    
    //create sub panels
    private JPanel panelForm = new JPanel(new GridLayout(4, 2, 10,10));    
    private JPanel panelButtons = new JPanel(new GridLayout(1, 4));    
    private JPanel panelRadType = new JPanel(new GridLayout(1, 3));
    
    //declare MainMenuFrame object, need it to hold reference
    private MainMenuFrame mainMenuObj;
    
    public SearchMemberFrame(MainMenuFrame menu)
    {        
        mainMenuObj = menu; //assign menu to obj
        
        tblMember = new JTable();  //create table
        tblMember.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //make sure table doesn't try to squeze columns  
        
        //make sure only one record at a time can be selected. Each record shall 
        //be a listener in their own right
        tblMember.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel rowSM = tblMember.getSelectionModel();
        
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                selectedRow = lsm.getMinSelectionIndex();
            }            
        });
        
        //add table to the scroll pane constructor
        scroll = new JScrollPane(tblMember, 
                                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        
        //set title, size, location, visibility, close and resizability      
        this.setTitle("Search Member");      
        this.setSize(MainMenuFrame.FRAME_WIDTH, MainMenuFrame.FRAME_HEIGHT);
        this.setLocationRelativeTo(null);  //place frame in center of screen      
        this.setVisible(true);  //visible
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //what to do on close      
        this.setResizable(false);  //constant size
                
        //add panels to content
        content.add(panelHeadline, BorderLayout.NORTH);
        content.add(panelCenter, BorderLayout.CENTER);
        
        //add sub panels to panelCenter
        panelCenter.add(panelForm, BorderLayout.NORTH);
        panelCenter.add(scroll, BorderLayout.CENTER);
        panelCenter.add(panelButtons, BorderLayout.SOUTH);
        
        //headline related
        panelHeadline.setBackground(new Color(70,130,180));
        lblHeadline.setFont(fHeadline);
        lblHeadline.setForeground(Color.white);
        panelHeadline.add(lblHeadline);  
        
        //set border to give a margin (top, left, bottom, right)
        panelHeadline.setBorder(new EmptyBorder(10, 5, 10, 5));  //create a margin
        panelForm.setBorder(new EmptyBorder(15, 325, 20, 325));  //create a margin
              
        //add components to panelForm
        panelForm.add(lblType);
        panelForm.add(panelRadType);  
        panelForm.add(lblId);
        panelForm.add(txfId);   
        panelForm.add(lblFirstName);
        panelForm.add(txfFirstName);
        panelForm.add(lblLastName);
        panelForm.add(txfLastName);        
        
        //add radio buttons to panel
        panelRadType.add(radTypeUnknown);
        panelRadType.add(radTypeAbled);
        panelRadType.add(radTypeDisabled);
        
        //add buttons to panel
        panelButtons.add(btnSearch);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnBack);
        
        //inactivate button
        btnEdit.setEnabled(false); 
        btnDelete.setEnabled(false);       
               
        //group radio buttons to act as a whole        
        typeGroup.add(radTypeUnknown);
        typeGroup.add(radTypeAbled);
        typeGroup.add(radTypeDisabled);
        
        //set tooltips
        setToolTips();
    
        //add actionlistener
        btnSearch.addActionListener(this); 
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);
        
        //add windowListener to the frame
        this.addWindowListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //check which button was clicked
        if (e.getSource() == btnSearch)
        {            
            doSearchMember(true);          
        }
        //check which button was clicked
        if (e.getSource() == btnEdit)
        {
            //check if a row has been clicked
            if (selectedRow > -1)
            {
                Member m = memberTblModel.getRow(selectedRow);
                doEditMember(m);   
                doSearchMember(true);
            }
            else  //no row has been clicked
            {
                JOptionPane.showMessageDialog(null, "Please select a row");              
            }            
        }
        //check which button was clicked
        if (e.getSource() == btnDelete)
        {
            //check if a row has been clicked
            if (selectedRow > -1)
            {
                //display confirm box to user (yes = 0, no = 1, cancel = 2)
                int confirmboxAnswer = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to delete?", 
                        null, JOptionPane.YES_NO_OPTION);
                
                //check confirmboxAnswer, yes/no to delete
                //If yes is pressed then confirmboxAnswer = zero. 
                if (confirmboxAnswer == 0)  //yes to delete
                {                    
                    //ok to delete
                    //assign the selected row to a member obj
                    Member m = memberTblModel.getRow(selectedRow);
                    doDeleteSelectedMember(m);  //delete the member
                    doSearchMember(false);  //to refresh
                }                
            }
            else  //no row has been clicked
            {
                JOptionPane.showMessageDialog(null, "Please select a row");         
            }
        }
        //check which button was clicked
        if (e.getSource() == btnBack)
        {
            this.setVisible(false);
            mainMenuObj.setVisible(true);            
        }
    }  //end actionPerformed   
    
    /**
     * Search for member(s) in database
     * @param popUp   boolean indicating if message need to display if search result is empty
     */
    public void doSearchMember(boolean popUp)
    {
        boolean okToDoSearch = true;
        WhereClause wc;
        ArrayList<WhereClause> whereClauseList = new ArrayList<>();
        
        //get user input
        String strId = txfId.getText().trim();
        int intId;
        String firstName = txfFirstName.getText().trim();
        String lastName = txfLastName.getText().trim();
        
        if (!Validation.isStrEmpty(strId))
        {            
            //check if id is valid
            if (Validation.isMemberIdValid(strId, txfId))
            {                
                //convert to int
                intId = Integer.parseInt(strId);
                wc = new WhereClause("memberId", intId);
                whereClauseList.add(wc);
            }
            else  //invalid input
            {                
                okToDoSearch = false;  //update flag
            }                    
        }
        if (!Validation.isStrEmpty(firstName))
        {
            wc = new WhereClause("firstName", firstName);  
            whereClauseList.add(wc);                
        }  
        if (!Validation.isStrEmpty(lastName))
        {
            wc = new WhereClause("lastName", lastName);  
            whereClauseList.add(wc);                
        }  
        if (radTypeAbled.isSelected())
        {
            wc = new WhereClause("memberType", "A");  
            whereClauseList.add(wc);                
        } 
        if (radTypeDisabled.isSelected())
        {
            wc = new WhereClause("memberType", "D");  
            whereClauseList.add(wc);                
        }    

        if (okToDoSearch)
        {            
            memberList = getMemberArrayList(whereClauseList);

            memberTblModel = new MemberTableModel(memberList);  //create member table model
            tblMember.setModel(memberTblModel);  //set model for the table  
        
            //loop through columns of tblMember
            for (int i = 0; i < memberTblModel.columnWidths.length; i++)
            {
                //set the width of each column
                tblMember.getColumnModel().getColumn(i).setPreferredWidth(memberTblModel.columnWidths[i]);
            }
             
            if (memberList.size() > 0)
            {
                //activate button
                btnEdit.setEnabled(true); 
                btnDelete.setEnabled(true);                 
            }
            else
            {
                if (popUp)  //check if user need informed
                {
                    //msg to user
                    JOptionPane.showMessageDialog(null, "Sorry, no match");
                }
                //activate button
                btnEdit.setEnabled(false); 
                btnDelete.setEnabled(false);                  
            }
            
            //show ok color
            txfId.setBackground(Color.white); 
        }
        else
        {
            //msg to user
            JOptionPane.showMessageDialog(null, "Invalid Id, please enter positive integer");            
        }  
           
    }  //end doSearchMember()
    
    //edit member details
    private void doEditMember(Member m)
    {
        MemberFrame mFrame = new MemberFrame(this, "Edit Member", "Edit Member Details", m);
        this.setVisible(false);        
    } 
    
    //delete a specific member
    private void doDeleteSelectedMember(Member m)
    {
        //create ski data access obj
        SkiDataAccess sda = new SkiDataAccess();
        
        sda.deleteMember(m.getMemberId());
    }
    
    //get all members from db put in arraylist
    private ArrayList<Member> getMemberArrayList(ArrayList<WhereClause> whereClauseList)
    {    
        //create ski data access obj
        SkiDataAccess sda = new SkiDataAccess();
        
        return sda.readAllMembers(whereClauseList);
    }
    
    //set tooltips for components
    private void setToolTips()
    {        
        lblId.setToolTipText("Positive integer");
        txfId.setToolTipText("Positive integer");
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
