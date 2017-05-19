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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import connection.SQL;

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

	/** Sign Up text. */
	private final static String SIGN_UP_TAB = "Sign Up";

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
        JPanel signUpTab = new JPanel();
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

        JButton allUsers = new JButton("All Users");
        allUsers.addActionListener(new ActionListener() {

            /**
             * Opens the "all users" page.
             * @param theEvent The event reference.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(GUI.this,
                    SQL.getAllUsers(),
                    "All Users", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutTab.add(allUsers);
        
        
        JTextField loginEmail = new JTextField("Email", TEXT_WIDTH);
        
        JTextField loginPassword = new JPasswordField("Password", TEXT_WIDTH);

        JButton loginSubmit = new JButton("Submit");        
        
        JTextField firstName = new JTextField("First Name", TEXT_WIDTH);
	     
        JTextField lastName = new JTextField("Last Name", TEXT_WIDTH);
	
        JTextField email = new JTextField("Email", TEXT_WIDTH);
	
        JTextField password = new JPasswordField("Password", TEXT_WIDTH);
	
        JTextField passwordConfirm = new JPasswordField("Confirm Password", TEXT_WIDTH);

        JButton submit = new JButton("Submit");
        
        loginTab.add(loginEmail);
        loginTab.add(loginPassword);
        loginTab.add(loginSubmit);
        
        signUpTab.add(firstName);
        signUpTab.add(lastName);
        signUpTab.add(email);
        signUpTab.add(password);
        signUpTab.add(passwordConfirm);
        
        loginSubmit.addActionListener(new ActionListener() {

            /**
             * Attempts to submit the information.
             * @param theEvent The event reference.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	User user = new User("", "",
            			loginEmail.getText(), loginPassword.getText());
            	int code = SQL.login(user);
            	user = SQL.getLastUser();
            	if (code == 0) {
            		int reply = JOptionPane.showConfirmDialog(GUI.this,
            			    "Email does not exist. Would you like to register instead?",
            			    "Register instead?",
            			    JOptionPane.YES_NO_OPTION);
            		if (reply == JOptionPane.YES_OPTION) {
            			email.setText(loginEmail.getText());
            			password.setText(loginPassword.getText());
                		JOptionPane.showMessageDialog(GUI.this,
                			    "Please use the sign up tab.", "",
                			    JOptionPane.PLAIN_MESSAGE);
            		}
            	} else if (code == 1) {
            		JOptionPane.showMessageDialog(GUI.this,
            			    "Welcome back, " + user.getFirstName() + " " + user.getLastName() + "!",
            			    "",
            			    JOptionPane.PLAIN_MESSAGE);
            	} else if (code == 2) {
            		JOptionPane.showMessageDialog(GUI.this,
            			    "Incorrect password - try again..",
            			    "Error",
            			    JOptionPane.ERROR_MESSAGE);
            	} else {
            		JOptionPane.showMessageDialog(GUI.this,
            			    "Error - could not access the login server.",
            			    "Error",
            			    JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        submit.addActionListener(new ActionListener() {

            /**
             * Attempts to submit the information.
             * @param theEvent The event reference.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (!password.getText().equals(passwordConfirm.getText())) {
            		JOptionPane.showMessageDialog(GUI.this,
            			    "Passwords do not match.",
            			    "Error",
            			    JOptionPane.ERROR_MESSAGE);
            	} else {
	            	User user = new User(firstName.getText(), lastName.getText(),
	            			email.getText(), password.getText());
	            	int code = SQL.login(user);
	            	if (code == 0) {
            			SQL.updateUser(user);
                		JOptionPane.showMessageDialog(GUI.this,
                			    "Welcome, " + firstName.getText() + " " + lastName.getText() + "!",
                			    "",
                			    JOptionPane.PLAIN_MESSAGE);
	            	} else if (code == 1 | code == 2) {
	            		JOptionPane.showMessageDialog(GUI.this,
	            			    "This email is already registered.",
	            			    "Error",
	            			    JOptionPane.ERROR_MESSAGE);
	            	} else {
	            		JOptionPane.showMessageDialog(GUI.this,
	            			    "Error - could not access the login server.",
	            			    "Error",
	            			    JOptionPane.ERROR_MESSAGE);
	            	}
            	}
            }
        });

        signUpTab.add(submit);
         
        tabbedPane.addTab(LOGIN_TAB, loginTab);
        tabbedPane.addTab(SIGN_UP_TAB, signUpTab);
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