package com.revature.models;

import com.revature.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserLogin {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
        return userToBeRegistered;
    }
    
    public boolean validate(User user) throws ClassNotFoundException{
    	boolean status = false;
		
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	try {
    		
    		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1_schema", "root", "root1234");
    		PreparedStatement ps = connection.prepareStatement("select * from member where username = ? and password = ? ");
    		ps.setString(1,user.getUsername() );
    		ps.setString(2, user.getPassword());
    		
    		ResultSet rs = ps.executeQuery();
    		status=rs.next();
    		
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		
    	}
    	
    			
    			
    	
    
    		
   
    	
  	return status;
    	
    }

	public Optional<User> getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
