package com.revature.basakServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/application");


        String username=request.getParameter("username");
		String password=request.getParameter("password");

		UserDAO userDao = new UserDAO();
		//System.out.println(username + password);
		
		Optional<User>  user = userDao.getByUsername(username);
		
//		System.out.println("----is user present? " + user.isPresent());
		if (user.isPresent() && user.get().getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("HomeServlet?username=" + username);
		} else {
			response.sendError(404, "Invalid Credentials");
		}
		
		

	}



	}


