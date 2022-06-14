package com.revature.repositories;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.revature.models.User;


//@WebServlet("/registerreg")
public class RegisterDAO {
	private String dburl="jdbc:mysql://localhost:3306/project1_schema";
	private String dbuname="root";
	private String dbpasssword="root1234";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	public void loadDriver(String dbdriver) {
	
	try {
		Class.forName(dbdriver);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
	
	public Connection getConnection() {
		
		Connection con=null;
		
		try {
		
			con = DriverManager.getConnection(dburl,dbuname, dbpasssword);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	public String insert(User user)
	
	{
		loadDriver(dbdriver);
		Connection con=getConnection();
		String result="Data entered successfully";
		
		
		String quary="INSERT INTO `member`(`username`, `password`,  `email`, `phone`) VALUES (?, ?, ?, ?)";
		try {
		
	 PreparedStatement ps=con.prepareStatement(quary);
	
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setLong(4, user.getPhone());
		ps.executeUpdate();
		
		
		}catch(SQLException e) {
			e.printStackTrace();
			result="Data not entered ";
		}
		return result;
		
		
	}
	
}




