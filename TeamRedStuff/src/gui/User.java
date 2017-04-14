package gui;
/*
 * TCSS 360 - Software Development Team Red
 * User object stores the user's name and email.
 */
/**
 * 
 * @version 1.5
 */
public class User {

	private String myEmail;
	
	private String myFirstName;
	
	private String myLastName;
	
	//User Constructor
	public User(final String theFirstName, final String theLastName,
			final String theEmail) {
		if(theFirstName == null || theLastName ==  null
				|| theEmail == null) {
			throw new IllegalArgumentException("Illegal Parameters!");
		}
		//another comment to test pull requests 
		//the needs of the many, outweigh the needs of the few -Spock
		
		myFirstName = theFirstName;
		myLastName = theLastName;
		myEmail = theEmail;
	}
	
	public String getFirstName(){
		return myFirstName;
	}
	
	public String getLastName(){
		return myLastName;
	}
	
	public String getEmail(){
		return myEmail;
	}
	
	public void setFirstName(final String newFirstName) {
		if (theEmail != null) {
			myFirstName = newFirstName;
		}
	
		
	public User (String firstName, String lastName, String email) {
		//fun comment
	}
	
	public void setLastName(final String newLastName) {
		if (theLastName != null) {
			myLastName = newLastName;
		}
	}
	
	public void setEmail(final String newEmail) {
		if (theEmail != null) {
		myEmail = newEmail;
		}
	}
}
