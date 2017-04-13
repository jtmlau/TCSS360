
public class User {

	private String myEmail;
	
	private String myFirstName;
	
	private String myLastName;
	
	//User Constructor
	public User (String theFirstName, String theLastName, String theEmail) {
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
	
	public void setFirstName(String newFirstName){
		myFirstName = newFirstName;
	}
	
	public void setLastName(String newLastName){
		myLastName = newLastName;
	}
	
	public void setEmail(String newEmail){
		myEmail = newEmail;
	}
}
