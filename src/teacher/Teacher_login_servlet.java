package com.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Teacher_login_servlet
 */
public class Teacher_login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_login_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			display(request,response);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			login(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void login(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
        
        
        String query="select username,password from faculty_login where username=? and password=?";
        
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			out.print("<script src=\"login_correct.js\"></script>\r\n");
			display(request,response);
		}
		else {
			out.print("<script src=\"login_incorrect.js\"></script>\r\n");
			out.print("<a href='Teacher_login_servlet'>LOGIN AGAIN PLS</a>");
		}
}
	static void display(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
		Statement stmt=con.createStatement();
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.write("UPDATE/DELETE THE STUDENTS DETAILS<br><br>");

		out.print("<a href='index.jsp'>TAKE ME TO HOME PAGE</a><br>");
		
		String query="select * from student_login";
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		out.print("<table border='1' width'100'");
        out.write("<tr><th>username</th><th>password</th><th>Update</th><th>Delete</th></tr>");
        
        while(rs.next()) {
        	String username=rs.getString("username");
        	String password=rs.getString("password");
        	out.print("<tr>"+
		    		   "<td>"+username+"</td>"
		    		   +"<td>"+password+"</td>"
		    		   +"<td><a href='/EWA_FINAL_PROJECT/Teacher_Update_Servlet?id="+username+"'>Update</a></td>"
		        	   +"<td><a href='/EWA_FINAL_PROJECT/Teacher_delete_servlet?id="+username+"'>delete</a></td>"
		    		   +"</tr>");
        }
        
	}
	
	}


