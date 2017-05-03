package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import gui.User;

import java.io.*;

/**
 * A class to handle SQL/database related operations.
 */

public class SQL {
	
	private static final String DB = "db";
	
	private static final String URL = "test.net";
	
	private static final String USER = "user";
	
	private static final String PASS = "abc";

	private static final Properties properties;
	static {
		properties = new Properties();
		properties.put("user", USER);
		properties.put("password", PASS);
	}
	
	public static Connection connection = null;
	
	/**
	 * Establishes a connection with the database.
	 * Called from the main method.
	 * @throws Exception If there is an error connecting to the database.
	 */
	public static synchronized void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + DB, properties);
			System.out.println("Successfully connected to the database.");
		} catch (Exception e) {
			System.out.println("Error connecting to the database: "+ e);
		}
	}
	
	/**
	 * Updates (or creates if it doesn't exist) the credentials of a user.
	 * Should be called upon any change to the user.
	 * @param theClient
	 */
	public static synchronized void updateUser(final User theClient) {
		removeFromDB(theClient);
		addToDB(theClient);
	}
	
	/**
	 * Attempts to log in the given client.
	 * @param theClient The user information that is attempting to log in.
	 * @return 0 if the email is not found, 1 if successful login, 2 if password doesn't match,
	 * and 3 if there is a misc login error.
	 */
	public static synchronized int login(final User theClient) {
		String query = "SELECT * FROM `users` WHERE email = \"" + theClient.getEmail() + "\"";
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				int id = results.getInt(1);
				String firstName = results.getString(2);
				String lastName = results.getString(3);
				String email = results.getString(4);
				String password = results.getString(5);
				if (password.equals(theClient.getPassword())) {
					return 1; //login success
				} else {
					return 2; //login failure
				}
			}
			return 0; //email not found
		} catch (SQLException e) {
			System.out.println("Login error: " + e);
			return 3; //error
		}
	}
	
	/**
	 * Saves the credentials of the given user.
	 * @param theClient The user to save.
	 */
	private static synchronized void addToDB(final User theClient) {
		try {
			connection.createStatement().execute(generateQuery(theClient));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the credentials of the user. Assumes email addresses are unique.
	 * @param theClient The user to delete.
	 */
	private static synchronized void removeFromDB(final User theClient) {
		try {
			connection.createStatement().execute(
					"DELETE FROM `users` WHERE `email` = '"
							+ theClient.getEmail() + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates an SQL query based on the credentials of the user.
	 * @param theClient The user to save.
	 */
	private static String generateQuery(final User theClient) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users (");
		sb.append("first_name, ");
		sb.append("last_name, ");
		sb.append("email, ");
		sb.append("password) ");
		sb.append("VALUES (");
		sb.append("'" + theClient.getFirstName() +"',");
		sb.append("'" + theClient.getLastName() +"',");
		sb.append("'" + theClient.getEmail() +"',");
		sb.append("'" + theClient.getPassword() +"')");
		return sb.toString();
	}
}
