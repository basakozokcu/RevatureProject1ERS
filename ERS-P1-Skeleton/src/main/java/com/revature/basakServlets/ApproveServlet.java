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

import com.revature.models.Role;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.ReimbursementService;

/**
 * Servlet implementation class ApproveServlet
 */
@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();

        String yes = request.getParameter("approve");
        Status status = Status.DENIED;
        if ("yes".equalsIgnoreCase(yes)) {
            status = Status.APPROVED;
        }
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        String username = (String)session.getAttribute("username");
        UserDAO userDao = new UserDAO();
        Optional<User> userOptional = userDao.getByUsername(username);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        	Role role = user.getRole();
     
        
        System.out.println("User= "+user);
        String result ="";

        if (role.equals(Role.FINANCE_MANAGER)) {
            ReimbursementService reimburseServ = new ReimbursementService();

            Reimbursement reimbursement = new Reimbursement();
            reimbursement.setId(id);
            reimbursement.setStatus(status);
            reimbursement.setResolver(user.getId());
            reimburseServ.update(reimbursement);

            result = "Reimbursement " + id + " is " +status.toString();
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
