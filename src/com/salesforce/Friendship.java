/**
 * 
 */
package com.salesforce;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author bhargav
 *
 */
public class Friendship implements Relationship {

	/**
	 * @param args
	 */
	private HashMap<String, HashSet<String>> frndMap = null;

	public Friendship() {
		frndMap =  new HashMap<String, HashSet<String>>();
	}

	@Override
	public void makeFriend(String frnd1, String frnd2) {
		// Set friends in the map
		if (frnd1 == null || frnd2 ==null || frnd1.equals(frnd2) ||
				frnd1.length() == 0 || frnd2.length() == 0) {
		    throw new IllegalArgumentException("Friends cannot be null");
		}
		frnd1 = frnd1.toLowerCase();
		frnd2 = frnd2.toLowerCase();
		if (frndMap.containsKey(frnd1)) {
			frndMap.get(frnd1).add(frnd2);
		} else {
			HashSet<String> frndSet = new HashSet<String>();
			frndSet.add(frnd2);
			frndMap.put(frnd1, frndSet);
		}
		// do the same for frnd 2
		if (frndMap.containsKey(frnd2)) {
			frndMap.get(frnd2).add(frnd1);
		} else {
			HashSet<String> frndSet = new HashSet<String>();
			frndSet.add(frnd1);
			frndMap.put(frnd2, frndSet);
		}
	}

	@Override
	public void unmakeFriend(String frnd1, String frnd2) {
		// remove frnds from each other
		frnd1 = frnd1.toLowerCase();
		frnd2 = frnd2.toLowerCase();
		if (frnd1 == null || frnd2 ==null) {
		    throw new IllegalArgumentException("Friends cannot be null");
		}
		if (frndMap.containsKey(frnd1)) {
			frndMap.get(frnd1).remove(frnd2);
		}
		if (frndMap.containsKey(frnd2)) {
			frndMap.get(frnd2).remove(frnd1);
		}
	}

	@Override
	public HashSet<String> getDirectFriends(String name) {
		name = name.toLowerCase();
		// return direct set of friends 
		if (frndMap.containsKey(name)) {
			return frndMap.get(name);
		} else {
			return null;
		}
	}

	@Override
	public HashSet<String> getIndirectFriends(String name) {
		// return only indirect frnds
		name = name.toLowerCase();
		if (frndMap.containsKey(name)) {
			HashSet<String> indirectFrnds = new HashSet<String>();
			for(String frnd: frndMap.get(name)) {
				indirectFrnds.addAll(frndMap.get(frnd));
			}
			// remove any direct friends and himself
			// this step can be optimized
			for (String frnd: frndMap.get(name)) {
				indirectFrnds.remove(frnd);
			}
			indirectFrnds.remove(name);
			return indirectFrnds;
		} else {
			return null;
		}
	}

}
