/*
 * TCSS 360 - Spring 2017
 * Team Red
 */

package gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import connection.SQL;

/**
 * The main class for the GUI.
 * @author Stan Hu
 * @version 11 April 2016
 */
public final class GUIMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private GUIMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new GUI().start();
                User tester = new User("john", "doe", "johndoe@gmail.com", "qwe123");
                SQL.connect();
                System.out.println("Log in code: " + SQL.login(tester));
                System.out.println("Log in code wrong pw: " + SQL.login(new User("john", "doe", "johndoe@gmail.com", "qwe1234")));
                System.out.println("Log in code no email: " + SQL.login(new User("john", "doe", "johndo3e@gmail.com", "qwe1234")));
                //SQL.updateUser(tester);
            }
        });
    }
    
    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }
    }
}
