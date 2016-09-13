package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Kirstine B. Nielsen. Student ID: 100527988
 * Date: 06.06.2016
 * MyButton.java
 * Version 1.0
 * Class for customize a button.
 */
public class MyButton extends JButton implements MouseListener
{    
    //instance variables
    private Font font_button = new Font("Verdana", Font.PLAIN, 26);
    private Font font_button_hover = new Font("Verdana", Font.BOLD, 30);
    private final Color COLOR_BLUE = new Color(70,130,180);
    
    /**
     * Constructor
     * @param btnText   The text on the button
     * @param fontSizeBasic   The button font size when button is not active
     * @param fontSizeBasicHover   The button font size when button is hovered over
     */
    public MyButton(String btnText, int fontSizeBasic, int fontSizeBasicHover)
    {
        super.setText(btnText);
        font_button = new Font("Verdana", Font.PLAIN, fontSizeBasic);
        font_button_hover = new Font("Verdana", Font.BOLD, fontSizeBasicHover);
        this.setFont(font_button);
        this.addMouseListener(this);
        this.setForeground(COLOR_BLUE);        
    }    
          
    //other methods  
    @Override
    public void mouseClicked(MouseEvent e) 
    {
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        this.setFont(font_button_hover);
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        this.setFont(font_button);
    }
 }
