package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class student_newregister_servlet
 */
public class student_newregister_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public student_newregister_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//doGet(request, response)
		//response.getWriter().append("NEW USER REGISTERED ").append(request.getContextPath());
		html(request,response);
	}
				

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			new_register(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void new_register(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
        
        String query="insert into student_login values(?,?)";
        
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        
        pst.executeUpdate();
        
        response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<script src=\"new_register.js\"></script>\r\n");
		out.print("<a href='Student_login_servlet'>NOW LOGIN INTO THE SYSTEM</a>");
	}
	
	static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.write("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "		<title>\r\n"
				+ "            YEAR BOOK SYSTEM - NEW STUDENT REGISTRATION\r\n"
				+ "        </title>\r\n"
				+ "		<link rel=\"stylesheet\" href=\"NewRegistrationCSS.css\">\r\n"
				+ "	</head>\r\n"
				+ "			\r\n"
				+ "        <body>\r\n"
				+ "			<div class=\"New-Registration\">\r\n"
				+ "				<h1>ONLINE UNIVERSITY YEAR BOOK SYSTEM</h1>\r\n"
				+ "                <h2>NEW STUDENT REGISTRATION</h2>\r\n"
				+ "				<form method=\"post\" action=\"student_newregister_servlet\">\r\n"
				+ "				\r\n"
				+ "				    <p>Username</p>\r\n"
				+ "                    <input type=\"text\" placeholder=\"Enter Username\" name=\"username\">\r\n"
				+ "				    <p>Password</p>\r\n"
				+ "                    <input type=\"password\" placeholder=\"Enter Password\" name=\"password\" >\r\n"
				+ "                    <input type=\"submit\" value=\"Create New Account\">\r\n"
				+ "				</form>\r\n"
				+ "			</div>\r\n"
				+ "		</body>\r\n"
				+ "</html>");
		}
}
