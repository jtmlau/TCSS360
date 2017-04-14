/*
 * TCSS 360 - Spring 2017
 * Team Red
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The GUI itself.
 * @author Stan Hu
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
			"Stan Hu", "Jimmy Best"
	};
	
    /**
     * The default frame size.
     */
	private static Dimension FRAME_SIZE = new Dimension(600, 400);

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
        
        final JPanel mainPanel = new JPanel();
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
        mainPanel.add(about);
        add(mainPanel, BorderLayout.CENTER);
        
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
