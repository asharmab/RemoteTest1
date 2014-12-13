package com.salesforce;

import java.util.Set;

public interface Relationship {
	// make friends
	public void makeFriend(String frnd1, String frnd2);
	
	// break friends
    public void unmakeFriend(String frnd1, String frnd2);
    
    // get list of direct friends
    public Set<String> getDirectFriends(String frnd1);
    
    // get indect friends
    public Set<String> getIndirectFriends(String frnd1);
}
