package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gui.User;

public class UserTest {

	User tester;
	
	@Before
	public void setUp() throws Exception {
		tester = new User("amanda", "aldrich", "amlaldrich@gmail.com");
	}

	@Test
	public void testUserFirstName() {
		User newUser = new User("amanda", "aldrich", "amlaldrich@gmail.com");
		assertEquals(tester.getFirstName(), newUser.getFirstName());
	}
	
	@Test
	public void testUserLastName() {
		User newUser = new User("amanda", "aldrich", "amlaldrich@gmail.com");
		assertEquals(tester.getLastName(), newUser.getLastName());
	}
	
	@Test
	public void testUserEmail() {
		User newUser = new User("amanda", "aldrich", "amlaldrich@gmail.com");
		assertEquals(tester.getEmail(), newUser.getEmail());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionFirstName(){
		User nullUser = new User(null, "Kirk", "enterprise@starfleet.org");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionLastName(){
		User nullUser = new User("James", null, "enterprise@starfleet.org");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testException(){
		User nullUser = new User("James", "Kirk", null);
	}
}	