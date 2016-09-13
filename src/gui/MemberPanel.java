package gui;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import classes.Abled;
import classes.Disabled;
import classes.Member;
import classes.Team;
import dal.SkiDataAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import validation.Validation;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 18.05.2016
 * MemberPanel.java
 * Version 1.0
 * Class for MemberPanel, to hold the form with input fields, radio buttons, check boxes etc
 */
public class MemberPanel extends JPanel implements ItemListener
{        
    //declare array list to hold Team objects
    private ArrayList<Team> teamList;	
    private final int nextAvailableID;  //to hold the id for next member
    
    //create arraylists to holde JTextField JLabel objects
    private ArrayList <JTextField> txfList= new ArrayList();
    private ArrayList <JLabel> labelList= new ArrayList();
    
    //combobox
    private JComboBox cboTeam;  //add the string array to the combo box constructor
    
    //label, chkbox, radio button
    private JLabel lblTeam = new JLabel("Choose Team");    
    private JLabel lblId = new JLabel("Member Id");
    private JLabel lblFirstName = new JLabel("First name");
    private JLabel lblLastName = new JLabel("Last name");
    private JLabel lblStreetNo = new JLabel("Street number");
    private JLabel lblStreetName = new JLabel("Street name");
    private JLabel lblSuburb = new JLabel("Suburb");   
    private JLabel lblPostcode = new JLabel("Postcode"); 
    private JLabel lblMobile = new JLabel("Mobile");
    private JLabel lblEmail = new JLabel("Email");
    private JLabel lblTotalRaces = new JLabel("Total Races");
    private JLabel lblTotalWins = new JLabel("Total Wins");  
    
    private JLabel lblIsFrontRunner = new JLabel("Do you assist as front runner?");   
    private JCheckBox chkIsFrontRunner = new JCheckBox(" Yes, I assist as front runner");  //nb. true would mean checked by default    
    
    private JLabel lblType = new JLabel("Membership type?");   
    private JRadioButton radTypeAbled = new JRadioButton("Abled skier", true);
    private JRadioButton radTypeDisabled = new JRadioButton("Disabled skier");
    
    //declare ButtonGroup for radio buttons.
    private ButtonGroup typeGroup = new ButtonGroup();
    
    //category for disabled skier
    private JLabel lblDisabilityCategory = new JLabel("Choose disability category");      
    private JRadioButton radStanding = new JRadioButton("Standing", true);
    private JRadioButton radSitting = new JRadioButton("Sitting");
    private JRadioButton radVisually = new JRadioButton("Visual");
    
    //declare ButtonGroup for radio buttons.
    private ButtonGroup disabilityCategoryGroup = new ButtonGroup();
    
    //need front runner skier
    private JLabel lblDisabilityFrontRunner = new JLabel("Do you need a front runner?");  
    private JCheckBox chkNeedFrontRunner = new JCheckBox(" Yes, I need a front runner");  //nb. true would mean checked by default
    
    //declare ButtonGroup for radio buttons.
    private ButtonGroup experienceGroup = new ButtonGroup();
    
    //experience level front runner
    private JLabel lblExperience = new JLabel("Your front runner experience");     
    private JRadioButton radHigh = new JRadioButton("High", true);
    private JRadioButton radLow = new JRadioButton("Low");
    
    //text fields
    private JTextField txfId = new JTextField(10);  //have to put int 10 to make it take space or else very tiny    
    private JTextField txfFirstName = new JTextField(10);
    private JTextField txfLastName = new JTextField(10);    
    private JTextField txfStreetNo = new JTextField(10);
    private JTextField txfStreetName = new JTextField(10);
    private JTextField txfSuburb = new JTextField(10);    
    private JTextField txfPostcode = new JTextField(10);
    private JTextField txfMobile = new JTextField(10);
    private JTextField txfEmail = new JTextField(10);    
    private JTextField txfTotalRaces = new JTextField(10);
    private JTextField txfTotalWins = new JTextField(10);
    
    //create font object
    private Font fLabel = new Font("Verdana", Font.BOLD, 14); 
    
    //create sub panels
    private JPanel panelFormContent= new JPanel();
    
    //create sub sub panels
    private JPanel panelRadMemberType = new JPanel(new GridLayout(1, 2));
    private JPanel panelRadDisabilityCategory = new JPanel(new GridLayout(1, 3));
    private JPanel panelRadExperience = new JPanel(new GridLayout(1, 2));
    
    private JPanel panelType = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel panelMemberDetails = new JPanel(new GridLayout(12, 2, 10, 10));
    private JPanel panelAbledFrontRunner = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel panelExperience = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel panelDisabledCategory = new JPanel(new GridLayout(1, 3, 10, 10));
    private JPanel panelDisabledFrontRunner = new JPanel(new GridLayout(1, 2, 10, 10));
    
    private JFrame parentFrame;
    
    //constructor
    public MemberPanel(JFrame frame, Member m)
    {                
        parentFrame = frame;
        
        setLayout(new BorderLayout());
        
        //find out what is the next available member id
        nextAvailableID = getNextAvailableMemberId();      
    	txfId.setText(Integer.toString(nextAvailableID));  //set id in txtfield
    	txfId.setEnabled(false); //enable txfId        
            
        //get all teams from db and add to array list
        teamList = getTheTeams();
        
        //array to hold text for each team
        String teamsForComboBox[] = new String[teamList.size()];
        
        for (int i = 0; i < teamList.size(); i++)
        {            
            //build text for drop down
            teamsForComboBox[i] = getTeamComboItemText(teamList.get(i));
        }
        
        cboTeam = new JComboBox(teamsForComboBox);      

        //populate the array list with textfield obj
        populateArrayListOfTxfObjects();
        
        //populate the array list with label obj
        populateArrayListOfLabelObjects();     
        
        //set layout
        panelFormContent.setLayout(new BoxLayout(panelFormContent, BoxLayout.Y_AXIS));
        
        //set border to give a margin (top, left, bottom, right)     
        panelType.setBorder(new EmptyBorder(15, 25, 20, 25));
        panelMemberDetails.setBorder(new EmptyBorder(5, 25, 20, 25));
        panelAbledFrontRunner.setBorder(new EmptyBorder(5, 25, 20, 25));
        panelExperience.setBorder(new EmptyBorder(5, 25, 20, 25));
        panelDisabledCategory.setBorder(new EmptyBorder(5, 25, 20, 25));
        panelDisabledFrontRunner.setBorder(new EmptyBorder(5, 25, 20, 25));                
        
        //add panels to panelFormContent
        panelFormContent.add(panelType);
        panelFormContent.add(panelMemberDetails);
        panelFormContent.add(panelAbledFrontRunner);
        panelFormContent.add(panelExperience);       
        panelFormContent.add(panelDisabledCategory);
        panelFormContent.add(panelDisabledFrontRunner);
       
        //add panel to this panel
        add(panelFormContent, BorderLayout.NORTH);
        
        //set font for labels 
        lblType.setFont(fLabel);        
        lblTeam.setFont(fLabel);
        lblId.setFont(fLabel);
        
        for (JLabel lbl : labelList)
        {
            lbl.setFont(fLabel);
        } 
        
        lblIsFrontRunner.setFont(fLabel);
        lblExperience.setFont(fLabel);
        lblDisabilityCategory.setFont(fLabel);
        lblDisabilityFrontRunner.setFont(fLabel);
                 
        //add to panel
        panelType.add(lblType);
        panelType.add(panelRadMemberType);       
        
        panelRadMemberType.add(radTypeAbled);
        panelRadMemberType.add(radTypeDisabled);
        
        panelMemberDetails.add(lblTeam);
        panelMemberDetails.add(cboTeam);        
        panelMemberDetails.add(lblId);
        panelMemberDetails.add(txfId);   
        
        for (int i = 0; i < txfList.size(); i++)
        {
            panelMemberDetails.add(labelList.get(i));
            panelMemberDetails.add(txfList.get(i));
        }   
        
        panelMemberDetails.add(lblTotalRaces);
        panelMemberDetails.add(txfTotalRaces);         
        panelMemberDetails.add(lblTotalWins);
        panelMemberDetails.add(txfTotalWins);
            
        panelAbledFrontRunner.add(lblIsFrontRunner);
        panelAbledFrontRunner.add(chkIsFrontRunner);
        
        panelExperience.add(lblExperience);
        panelExperience.add(panelRadExperience);
        
        panelRadExperience.add(radHigh);        
        panelRadExperience.add(radLow);        
        
        panelDisabledCategory.add(lblDisabilityCategory);
        panelDisabledCategory.add(panelRadDisabilityCategory);
        
        panelRadDisabilityCategory.add(radStanding);  
        panelRadDisabilityCategory.add(radSitting);  
        panelRadDisabilityCategory.add(radVisually);
        
        panelDisabledFrontRunner.add(lblDisabilityFrontRunner);
        panelDisabledFrontRunner.add(chkNeedFrontRunner);
                
        //hide panels
        panelDisabledCategory.setVisible(false);
        panelDisabledFrontRunner.setVisible(false);
        panelExperience.setVisible(false); 
        
        //group radio buttons
        disabilityCategoryGroup.add(radStanding);
        disabilityCategoryGroup.add(radSitting);
        disabilityCategoryGroup.add(radVisually);
               
        //group radio buttons to act as a whole
        typeGroup.add(radTypeAbled);
        typeGroup.add(radTypeDisabled);
        
        //group radio buttons
        experienceGroup.add(radHigh);
        experienceGroup.add(radLow);   
        
        //set the tooltips
        setToolTips();
        
        //register item listener need only for one radButton per group as they work as a whole
        radTypeAbled.addItemListener(this);
        
        //register item listener need only for one radButton per group as they work as a whole
        radVisually.addItemListener(this);
        
        //register item listener
        chkIsFrontRunner.addItemListener(this);
        
        //check if a member is passed into constructor
        if (m != null)  //edit relevant
        {            
            //member passed in so we need to load details too 
            loadMemberDetails(m);            
        } 
            
        //adding ghosting
        addGhostingToTxtFields();    //add ghosting to help user        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        int selected = e.getStateChange();  //return an integer 1 or 0, selected or not        
        
        if (e.getSource() == radTypeAbled)
        {
            if (selected == ItemEvent.SELECTED)  //selected
            {       
                //show or hide specific panels
                panelAbledFrontRunner.setVisible(true);
                panelDisabledCategory.setVisible(false);
                panelDisabledFrontRunner.setVisible(false);
                
                //check if front runner is selected
                if (chkIsFrontRunner.isSelected())
                {
                    panelExperience.setVisible(true);
                } 
            }            
            else  //disabled
            {       
                //show or hide specific panels
                panelAbledFrontRunner.setVisible(false);
                panelExperience.setVisible(false);
                panelDisabledCategory.setVisible(true);
                
                //check if visually impaired is selected
                if (radVisually.isSelected())
                {
                    panelDisabledFrontRunner.setVisible(true);
                }         
            }  
        }      
        if (e.getSource() == radVisually)
        {
            if (selected == ItemEvent.SELECTED)  //selected
            {       
                //show or hide specific panel
                panelDisabledFrontRunner.setVisible(true);                
            }
            else
            {        
                //show or hide specific panel
                panelDisabledFrontRunner.setVisible(false);      
            }            
        }         
        if (e.getSource() == chkIsFrontRunner)
        {
            if (selected == ItemEvent.SELECTED)  //selected
            {       
                //show or hide specific panel
                panelExperience.setVisible(true);              
            }
            else
            {        
                //show or hide specific panel
                panelExperience.setVisible(false);  
            }            
        }
    }
    
    /**
     * Populate string array with input to text fields for a member object
     * @return   All input in text fields arranged in an array of Strings
     */
    private String[] populateArrayOfTxfInput()
    {     
        //amount of txt fields that we need to validate
        final int TXT_FIELD_AMOUNT = 10;
        
        //create array to hold input
        String inputDataList[] = new String[TXT_FIELD_AMOUNT];
        
        //all input comes in as a string
        String firstName;
        String lastName;
        String stNo;
        String stName;
        String suburb;   
        String postcode;        
        String mobile;
        String email;
        String totalRaces;
        String totalWins;

        //get values
        firstName = txfFirstName.getText().trim();
        lastName = txfLastName.getText().trim();
        stNo = txfStreetNo.getText().trim();
        stName = txfStreetName.getText().trim();
        suburb = txfSuburb.getText().trim();    
        postcode = txfPostcode.getText().trim();       
        mobile = txfMobile.getText().trim();
        email = txfEmail.getText().trim();
        totalRaces = txfTotalRaces.getText().trim();
        totalWins = txfTotalWins.getText().trim();        
        
        //insert in array
        inputDataList[0] = firstName;
        inputDataList[1] = lastName;
        inputDataList[2] = stNo;
        inputDataList[3] = stName;
        inputDataList[4] = suburb;
        inputDataList[5] = postcode;
        inputDataList[6] = mobile;
        inputDataList[7] = email;
        inputDataList[8] = totalRaces;
        inputDataList[9] = totalWins;
        
        return inputDataList;
    }
    
    /**
     * Populate an ArrayList with text field objects corresponding to details about member
     */
    private void populateArrayListOfTxfObjects()
    {
        //add objects to Array list        
        txfList.add(txfFirstName);
        txfList.add(txfLastName);
        txfList.add(txfStreetNo);
        txfList.add(txfStreetName);
        txfList.add(txfSuburb);
        txfList.add(txfPostcode);
        txfList.add(txfMobile);
        txfList.add(txfEmail);
        txfList.add(txfTotalRaces);
        txfList.add(txfTotalWins);        
    }
    
    /**
     * Populate ArrayList with label objects corresponding to details about member
     */
    private void populateArrayListOfLabelObjects()
    {
        //add objects to Array list        
        labelList.add(lblFirstName);
        labelList.add(lblLastName);
        labelList.add(lblStreetNo);
        labelList.add(lblStreetName);
        labelList.add(lblSuburb);
        labelList.add(lblPostcode);
        labelList.add(lblMobile);
        labelList.add(lblEmail);
        labelList.add(lblTotalRaces);
        labelList.add(lblTotalWins);
    }
    
    /**
     * Create a member
     * @return   A member object either an Abled or Disabled
     */
    public Member createMemberBasedOnTxfData()
    {
        Member m;           
        int intId;   //member id 
        
        //get array of textfield data
        String [] inputDataList = populateArrayOfTxfInput();

        //get data
        String id = txfId.getText().trim();
        
        //convert id to int
        intId = Integer.parseInt(id);
            
        //parse totalRaces and totalWins to integers
        int races = Integer.parseInt(inputDataList[8]);
        int wins = Integer.parseInt(inputDataList[9]);
        
        //check which member type to create, abled or disabled
        if (radTypeAbled.isSelected())
        {
            m = createAbledMember(intId, races, wins, inputDataList);
        }
        else  //disabled
        {
            m = createDisabledMember(intId, races, wins, inputDataList);            
        }
        
        return m;        
    }  //end createMember()
    
    /**
     * Create Abled member object
     * @param id   Member id for specific member
     * @param races   Number of races done for specific member
     * @param wins   Number of wins for specific member
     * @param inputDataList   Details for a member object put in an array of Strings
     * @return   A member object
     */
    private Member createAbledMember(int id, int races, int wins, String [] inputDataList)
    {
        //declare member obj to return
        Member m;
        
        boolean isFrontrunner = false;  //flag
        String experience = null;

        //is front runner checked
        if (chkIsFrontRunner.isSelected())
        {
            isFrontrunner = true;
        }

        if (isFrontrunner)
        {
            //experience
            if(radHigh.isSelected())
            {
                experience = Abled.HIGH;
            }
            else
            {
                experience = Abled.LOW;                        
            }
        }

        //instantiate Abled obj
        m = new Abled(isFrontrunner, experience, id, 
                            inputDataList[0], inputDataList[1], 
                            inputDataList[2], inputDataList[3],  
                            inputDataList[4], Integer.parseInt(inputDataList[5]),
                            inputDataList[6], inputDataList[7], 
                            races, wins);
        
        return m;  
              
    }  //end createAbledMember()
    
    /**
     * Create Disabled member object
     * @param id   Member id for specific member
     * @param races   Number of races done for specific member
     * @param wins   Number of wins for specific member
     * @param inputDataList   Details for a member object put in an array of Strings
     * @return   A member object
     */
    private Member createDisabledMember(int id, int races, int wins, String [] inputDataList)
    {        
        //declare member obj to return
        Member m;
                    
        String disabilityType;
        boolean isFrontRunnerNeeded = false;

        //check disability category 
        if (radVisually.isSelected())
        {
            disabilityType = Disabled.VISUAL_IMPAIRED;

            //check if front runner is needed
            if (chkNeedFrontRunner.isSelected())
            {
                isFrontRunnerNeeded = true;
            }
        }
        else if (radStanding.isSelected())
        {
            disabilityType = Disabled.STANDING;                    
        }
        else  //sitting
        {
            disabilityType = Disabled.SITTING;                    
        }

        //instantiate Disabled obj
        m = new Disabled(disabilityType, isFrontRunnerNeeded, id, 
                            inputDataList[0], inputDataList[1], 
                            inputDataList[2], inputDataList[3],  
                            inputDataList[4], Integer.parseInt(inputDataList[5]), 
                            inputDataList[6], inputDataList[7], 
                            races, wins);
        
        return m;  
    }  //end createDisabledMember()
    
    /**
     * Get the next available member id in database
     * @return   Next available member id to be given to a new member
     */
    private int getNextAvailableMemberId()
    {
        //create ski data access obj
        SkiDataAccess skiDataAccess = new SkiDataAccess();
        
        //get the max value in column for member id and add one
        int nextId = skiDataAccess.getMaxValueMemberId() + 1;
        
        return nextId;
    }
    
    /**
     * Get all Team objects in database
     * @return   All Team objects in database represented in an ArrayList
     */
    private ArrayList<Team> getTheTeams()
    {         
        //create ski data access obj
        SkiDataAccess skiDataAccess = new SkiDataAccess();    
    
        //get all teams from db as an array     
        return skiDataAccess.readAllTeams(null);
    }
    
    /**
     * Get team id that correspond to the team in a drop down menu
     * @return   id of team selected in a drop down menu
     */
    public int getSelecetedTeamId()
    {        
        //get selected team and cast it to string example "2  Sydney ski club"
        String strTeamText = (String) cboTeam.getSelectedItem();        
        String strTeamId = "";
        int intTeamId;

        //build string containing the id integer. keep loop until space
        for (int i = 0; i < strTeamText.length(); i++)
        {                    
            if (strTeamText.charAt(i) == ' ')
            {
                break;
            }
            else
            {
                strTeamId += strTeamText.charAt(i);
            }
        }

        //convert to integer
        intTeamId = Integer.parseInt(strTeamId);
        
        return intTeamId;
    }
    
    /**
     * Check if all input is valid
     * @return   True if all input in add/edit member is valid. Else false
     */
    public boolean isAllInputValid()
    {
        boolean isInputValid;

        //get array of txf data
        String inputDataList[] = populateArrayOfTxfInput();

        //validate user input
        isInputValid = Validation.isAllInputValid(inputDataList, txfList);
        
        return isInputValid;        
    }  //end isAllInputValid()
    
    /**
     * Fill in the member details of certain member object in the text fields in edit member
     * @param m   Member object that you want to load details about
     */
    private void loadMemberDetails(Member m)
    {
        //set text fields    
    	txfId.setText(Integer.toString(m.getMemberId()));  //set id in txtfield        
        txfFirstName.setText(m.getFirstName());
        txfLastName.setText(m.getLastName());
        txfStreetNo.setText(m.getAddress().getStreetNo());
        txfStreetName.setText(m.getAddress().getStreetName());
        txfSuburb.setText(m.getAddress().getSuburb());
        txfPostcode.setText(Integer.toString(m.getAddress().getPostCode()));
        txfMobile.setText(m.getMobile());
        txfEmail.setText(m.getEmail());
        txfTotalRaces.setText(Integer.toString(m.getTotalRaces()));
        txfTotalWins.setText(Integer.toString(m.getTotalWins()));
        
        //check if abled
        if (m instanceof Abled)
        {
            Abled a = (Abled) m;  //cast
            
            radTypeAbled.setSelected(true);
            
            //check if frontrunner
            if (a.getIsFrontRunner())
            {
                chkIsFrontRunner.setSelected(true);
                
                //set radio button for experience
                if(a.getExperience().equals("High"))
                {
                    radHigh.setSelected(true);
                }
                else
                {
                    radLow.setSelected(true);
                }
            }
        }
        else  //disabled
        {
            Disabled d = (Disabled) m;  //cast
            
            radTypeDisabled.setSelected(true);
            
            //set radiobuttons for disability category
            if (d.getDisabilityType().equals("Visually impaired"))
            {
                radVisually.setSelected(true);
                
                //set chkbox for is front runner needed
                if (d.getIsFrontRunnerNeeded())
                {
                    chkNeedFrontRunner.setSelected(true);
                }
            }
            else if (d.getDisabilityType().equals("Standing"))
            {
                radStanding.setSelected(true);                
            }
            else  //sitting
            {
                radSitting.setSelected(true);                
            }   
        }
        
        //select ski team
        cboTeam.setSelectedItem(getTeamComboItemText(m.getTeam()));   
        
    }  //end loadMemberDetails()
    
    /**
     * Add ghosting to text fields in add/edit member frame
     */
    private void addGhostingToTxtFields()
    {          
        GhostText ghostFirstName = new GhostText(txfFirstName, "2 - 20 characters (a-z)");
        GhostText ghostLastName = new GhostText(txfLastName, "2 - 30 characters (a-z), no spaces");
        GhostText ghostStreetName = new GhostText(txfStreetName, "2 - 30 characters (a-z)");
        GhostText ghostSuburb = new GhostText(txfSuburb, "2 - 30 characters (a-z)");
        GhostText ghostPostCode = new GhostText(txfPostcode, "Excactly 4 digits, 1000-9999");
        GhostText ghostMobile = new GhostText(txfMobile, "Excactly 10 digits");
        GhostText ghostEmail = new GhostText(txfEmail, "Enter valid email (max 30 characters)");
        GhostText ghostRaces = new GhostText(txfTotalRaces, "Integer 0 - 99999");
        GhostText ghostWins = new GhostText(txfTotalWins, "Integer 0 - 99999, less than or equal to Total races");
    }
    
    /**
     * Build a string consisting of details from specific Team object.
     * @param t   Team object which details you want to build string from
     * @return   String containing id and name of the Team object passed in
     */
    private String getTeamComboItemText(Team t)
    {
        //build text for drop down
        return t.getTeamId() + "  " + t.getTeamName();        
    }
    
    /**
     * Set the tool tips for components; labels, text fields, radio buttons
     */
    private void setToolTips()
    {    
        //set mouseover tooltips component
        lblId.setToolTipText("This field is system generated");
        txfId.setToolTipText("This field is system generated");
        lblType.setToolTipText("Choose whether you are disabled (e.g. missing one leg, sit skier, blind) or abled skier"); 
        radTypeAbled.setToolTipText("Abled: You compete in normal category");
        radTypeDisabled.setToolTipText("Disabled: You compete in disabled category");
        lblFirstName.setToolTipText("First name 2 - 20 characters (a-z)");
        txfFirstName.setToolTipText("First name 2 - 20 characters (a-z)");
        lblLastName.setToolTipText("Last name 2 - 30 characters (a-z), no spaces");
        txfLastName.setToolTipText("Last name 2 - 30 characters (a-z), no spaces");
        lblStreetNo.setToolTipText("Street number 1 - 15 characters");
        txfStreetNo.setToolTipText("Street number 1 - 15 characters");
        lblStreetName.setToolTipText("Street name 2 - 30 characters (a-z)");
        txfStreetName.setToolTipText("Street name 2 - 30 characters (a-z)");
        lblSuburb.setToolTipText("Suburb 2 - 30 characters (a-z)");
        txfSuburb.setToolTipText("Suburb 2 - 30 characters (a-z)");
        lblPostcode.setToolTipText("Postcode excactly 4 digits, 1000-9999");
        txfPostcode.setToolTipText("Postcode excactly 4 digits, 1000-9999");
        lblMobile.setToolTipText("Mobile excactly 10 digits");
        txfMobile.setToolTipText("Mobile excactly 10 digits");
        lblEmail.setToolTipText("Enter valid email (max 30 characters)");
        txfEmail.setToolTipText("Enter valid email (max 30 characters)");
        lblTotalRaces.setToolTipText("How many races have you completed? Integer 0 - 99999");
        txfTotalRaces.setToolTipText("How many races have you completed? Integer 0 - 99999");
        lblTotalWins.setToolTipText("How many races have you won? Integer 0 - 99999, cannot be more than Total races");
        txfTotalWins.setToolTipText("How many races have you won? Integer 0 - 99999, cannot be more than Total races");
        lblIsFrontRunner.setToolTipText("Check if you assist as front runner for Vision impaired skier");
        lblDisabilityFrontRunner.setToolTipText("Check if you need a front runner when you ski");        
    }
    
    /**
     * Clear text fields in add/edit member frame form
     */
    public void clearForm()
    {        
        //loop through the list
        for (int i = 0; i < txfList.size(); i++)
        {
            //clear all text fields
            txfList.get(i).setText("");
            txfList.get(i).setBackground(Color.WHITE);
            txfList.get(i).setForeground(Color.BLACK);    
            
            //triggers focus gained (need it for the ghost text)
            txfList.get(i).requestFocusInWindow();  
        }  
        
        cboTeam.requestFocusInWindow();
    }  //end clearForm()
    
}  //end class
