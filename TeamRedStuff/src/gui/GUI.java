/*
 * TCSS 360 - Spring 2017
 * Team Red
 */

package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The GUI itself.
 * @author Stan Hu, Taylor Riccetti
 * @version 11 April 2016
 */
public class GUI extends JFrame {

    /**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = 2941318999657277463L;
	
	/**
	 * A list of the contributors' names.
	 */
	private static final String NAMES[] = {
			"Stan Hu", "Jimmy Best", "Amanda Aldrich", "Taylor Riccetti", "Joshua Lau"
	};
	
    /**
     * The default frame size.
     */
	private static Dimension FRAME_SIZE = new Dimension(600, 400);

	/** Login tab text. */
	private final static String LOGIN_TAB = "Login";

	/** About tab text. */
	private final static String ABOUT_TAB = "About";
	
	/** Textbox width. */
	private static final int TEXT_WIDTH = 20;
 
	public GUI() {
        super("GUI Test");
       
    }
	
    /**
     * Displays a simple JFrame.
     */
    public void start() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(FRAME_SIZE);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel loginTab = new JPanel();
        JPanel aboutTab = new JPanel();

        JButton about = new JButton("About");
        about.addActionListener(new ActionListener() {

            /**
             * Opens the "about" page.
             * @param theEvent The event reference.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(GUI.this,
                    constructNames(),
                    "About", 
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/strawberry-icon.png"));
            }
        });
        aboutTab.add(about);
             
        JTextField firstName = new JTextField("First Name", TEXT_WIDTH);
	     
        JTextField lastName = new JTextField("Last Name", TEXT_WIDTH);
	
        JTextField email = new JTextField("Email", TEXT_WIDTH);
        loginTab.add(firstName);
        loginTab.add(lastName);
        loginTab.add(email);
        JButton submit = new JButton("Submit");

        loginTab.add(submit);
	    // TO-DO add listener to submit button
         
        tabbedPane.addTab(LOGIN_TAB, loginTab);
        tabbedPane.addTab(ABOUT_TAB, aboutTab);

        add(tabbedPane, BorderLayout.CENTER);
    	
        setVisible(true);
    }
     
    /**
     * A helper method to loop through the names and add them to a string.
     * @return A string representation of all the contributor names, to be shown in the
     * "about" panel.
     */
    private String constructNames() {
        final StringBuilder sb = new StringBuilder(255);
        sb.append("Contributors:\n");
        for (final String s : NAMES) {
            sb.append(s);
            sb.append('\n');
        }
        return sb.toString();
    } 
    
}