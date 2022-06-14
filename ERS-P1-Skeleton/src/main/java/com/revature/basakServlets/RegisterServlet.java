package com.revature.basakServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		UserService userServ = new UserService();
		User userToRegister = new User();
		
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
	//	Integer phone = Integer.parseInt(request.getParameter("phone"));   
		String email = request.getParameter("email");
		
		userToRegister.setUsername(username);
		userToRegister.setPassword(password);
		userToRegister.setFirstName(firstname);
		userToRegister.setLastName(lastname);
		//userToRegister.setPhone(phone);
		userToRegister.setEmail(email);
		
		
		if (role.equalsIgnoreCase("employee"))
			userToRegister.setRole(Role.EMPLOYEE);
		else if (role.equalsIgnoreCase("Finance Manager"))
			userToRegister.setRole(Role.FINANCE_MANAGER);
		
		User registeredUser = userServ.create(userToRegister);
		
		
		if (registeredUser !=null) {
			response.sendRedirect("login.html");
		}
		}
		catch (Exception e) {
			
		//e.printStackTrace();	
		response.sendRedirect("registererror.jsp");
			
		}
	}

		
		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);  
         
        }
        
       
			

	}

