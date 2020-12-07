package com.capg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		//Servlet configuration
		description = "Login Servlet Testing",
		urlPatterns = {"/LoginServlet"},
		initParams = {
				@WebInitParam(name = "user", value = "Tushar"),
				@WebInitParam(name = "password", value = "open")
		}
)
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Getting request parameters
		String user = request.getParameter("user");
		String nameRegex = "^[A-Z]{1}[a-zA-Z\\s]{2,}$";
		String password = request.getParameter("password");
		
		//getting servlet confg init parameters
		String userID = getServletConfig().getInitParameter("user");
		String checkPassword = getServletConfig().getInitParameter("password");
		
		if(!user.matches(nameRegex)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color = red>Name must start with capital letter and should have more than 3 letters!!</font>");
			rd.include(request, response);
		}
		else if(userID.equals(user) && checkPassword.equals(password)) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color = red>Incorrect User Name or Password!!</font>");
			rd.include(request, response);
		}
	}

}
