/**
 * 
 */
package com.salesforce;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author bhargav
 *
 */
public class FriendshipTests {
	
	ExpectedException exception = ExpectedException.none();
	
	@Test(expected= IllegalArgumentException.class)
	public void testNegativeMakeFriends() {
		exception.expect(IllegalArgumentException.class);
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "bhargav");
	}


	@Test
	public void testPositiveDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    String[] frnds = {"andy", "josh"};
	    assertTrue(frndShip.getDirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}

	@Test
	public void testNegativeTypoDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    String[] frnds = {"andy", "jsh"}; // typo in name
	    assertFalse(frndShip.getDirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}
	
	@Test
	public void testNegativeMissingValueDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    String[] frnds = {"andy"}; // missing friend
	    assertFalse(frndShip.getDirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}
	
	@Test
	public void testNegativeMissingKeyDirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    assertNull(frndShip.getDirectFriends("bob"));
	}
	
	@Test
	public void testPositiveIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    frndShip.makeFriend("andy", "bob");
	    frndShip.makeFriend("josh", "alice");
	    String[] frnds = {"alice", "bob"};
	    assertTrue(frndShip.getIndirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}
	
	@Test
	public void testPositiveUpperCaseIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("BARGAV", "josh");
	    frndShip.makeFriend("Andy", "BoB");
	    frndShip.makeFriend("josh", "Alice");
	    String[] frnds = {"alice", "bob"};
	    assertTrue(frndShip.getIndirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}

	@Test
	public void testNegativeUpperNameIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "Andy");
	    frndShip.makeFriend("bargav", "josh");
	    frndShip.makeFriend("andy", "bob");
	    frndShip.makeFriend("josh", "alice");
	    String[] frnds = {"alice", "bob"};
	    assertTrue(frndShip.getIndirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}
	
	@Test
	public void testNegativeIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    frndShip.makeFriend("andy", "bob");
	    frndShip.makeFriend("josh", "alice");
	    String[] frnds = {"alice"};
	    assertFalse(frndShip.getIndirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}

	@Test
	public void testNegativeEmptySpaceIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", "andy");
	    frndShip.makeFriend("bargav", "josh");
	    frndShip.makeFriend("andy", "bob");
	    frndShip.makeFriend("josh", "  ");
	    String[] frnds = {"  ", "bob"};
	    assertTrue(frndShip.getIndirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}

	@Test
	public void testNegativeZeroLengthIndirectFriends() {
	    Friendship frndShip = new Friendship();
	    frndShip.makeFriend("bargav", " ");
	    frndShip.makeFriend("bargav", "josh");
	    frndShip.makeFriend("andy", "bob");
	    frndShip.makeFriend("josh", "alice");
	    String[] frnds = {"", "bob"};
	    assertFalse(frndShip.getIndirectFriends("bargav").equals(new HashSet<String>(Arrays.asList(frnds))));
	}

}
