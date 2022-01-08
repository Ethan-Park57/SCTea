//package sctea;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Dispatcher")
public class Dispatcher extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email.contentEquals("") || password.contentEquals("")) { // later will also need to check that the user exists in our database
			out.println("<h4> Empty paramters are not allowed </h4>");
			request.getRequestDispatcher("login.html").include(request, response);
		} else {
			request.getRequestDispatcher("Submitted").forward(request, response);
		}
	}
	
}
