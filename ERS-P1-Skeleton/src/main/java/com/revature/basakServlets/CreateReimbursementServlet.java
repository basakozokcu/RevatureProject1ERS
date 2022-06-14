package com.revature.basakServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

/**
 * Servlet implementation class CreateReimbursementServlet
 */
@WebServlet("/CreateReimbursementServlet")
public class CreateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ReimbursementService reimbursementService = new ReimbursementService();
		
		PrintWriter out = response.getWriter();
		UserService userServ = new UserService();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("username in create servlet: "+username);
		User user = null;
		user = userServ.getByUsername(username).get();
		int id=user.getId();
		
		
		Reimbursement createReimbursement = new Reimbursement();
		double amount = Double.parseDouble(request.getParameter("amount"));
		String description = request.getParameter("description");
		String message = null;
		//if (authServ.exampleRetrieveCurrentUser().isPresent()) {
		//	user = authServ.exampleRetrieveCurrentUser().get();
		//}
		//System.out.println("username is = "+username);
		createReimbursement.setAmount(amount);
		createReimbursement.setDescription(description);
		createReimbursement.setAuthor(id);
		
		System.out.println("reimbursement object is: "+createReimbursement);
		
		Reimbursement reimbursement = reimbursementService.create(createReimbursement);
		if (reimbursement != null) {
			message = "Reimbursement created and registered into database!";
		}
		System.out.println(message);
		
		if (username != null && reimbursement !=null) {
			response.sendRedirect("HomeServlet?username="+username);
		} else {
			out.println("An error has occurred while submitting Reimbursement Request. Click <a href='login.html'>Here</a> to go back to Login Page");
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
