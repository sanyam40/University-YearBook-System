package com.student;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Student_login_servlet
 */
public class Student_login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_login_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		html(request,response);
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
        
        String query="select username,password from student_login where username=? and password=?";
        
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			HttpSession session = request.getSession();
			  session.setAttribute("user", username);
			out.print("<script src=\"login_correct.js\"></script>\r\n");
			display(request,response);
		}
		else {
			out.print("<script src=\"login_incorrect.js\"></script>\r\n");
			out.print("<a href='Student_login_servlet'>LOGIN AGAIN PLS</a>");
			
		}
}
	static void display(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
		Statement stmt=con.createStatement();
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String query="select * from main_data";
		PreparedStatement pst = con.prepareStatement(query);
		
		ResultSet rs = pst.executeQuery();
		//out.print("<table border='1' width'100'");
        //out.write("<tr><th>SNO</th><th>Id</th><th>Acheived_By</th><th>Name</th><th>Task</th><th>Achievements</th><th>Year</th><th>Update</th><th>Delete</th></tr>");
		out.write("<!DOCTYPE html>\r\n"
        		+ "<html>\r\n"
        		+ "<head>\r\n"
        		+ "<meta charset=\"ISO-8859-1\">\r\n"
        		+ "<title>Achievements List</title>\r\n"
        		+ "<link rel=\"stylesheet\" href=\"table_css.css\">\r\n"
        		+ "</head>\r\n"
        		+ "<body>\r\n"
        		+ "<h3 align=\"center\">ACHEIVMENTS LIST</h3>\r\n"
        		+ "<table class=\"styled-table\">\r\n"
        		+ "<tr><th>SNO</th><th>Id</th><th>Acheived_By</th><th>Name</th><th>Task</th><th>Achievements</th><th>Year</th><th>Update</th><th>Delete</th></tr>");
		
		while(rs.next()) {
			int sno=rs.getInt("sno");
			int idd=rs.getInt("id");
			String id=Integer.toString(idd);
			String Acheived_By=rs.getString("Acheived_By");
			String Name=rs.getString("Name");
			String Task=rs.getString("Task");
			String Achievements=rs.getString("Achievements");
			int year=rs.getInt("Year");
			
			out.print("<tr>"+
		    		   "<td>"+sno+"</td>"
		    		   +"<td>"+id+"</td>"
		    		   +"<td>"+Acheived_By+"</td>"
		    		   +"<td>"+Name+"</td>"
		    		   +"<td>"+Task+"</td>"
		    		   +"<td>"+Achievements+"</td>"
		    		   +"<td>"+year+"</td>"
		    		   +"<td><a href='/EWA_FINAL_PROJECT/Student_Update_servlet?id="+id+"'>Update</a></td>"
		        	   +"<td><a href='/EWA_FINAL_PROJECT/Student_delete_servlet?id="+id+"'>delete</a></td>"
		    		   +"</tr>");
		}
		out.print("</table>"+
		"</body>"+
		"</html>");
		out.print("<a class =\"links\" href='Student_add_servlet'>ADD NEW DATA</a>");
		out.print("<a class =\"links\" href='index.jsp'>HOME PAGE</a>");
	}
	
	static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.write("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "		<title>\r\n"
				+ "            YEAR BOOK SYSTEM - STUDENT LOGIN\r\n"
				+ "        </title>\r\n"
				+ "		<link rel=\"stylesheet\" href=\"StudentLoginCSS.css\">\r\n"
				+ "	</head>\r\n"
				+ "			\r\n"
				+ "        <body>\r\n"
				+ "			<div class=\"Student-Login\">\r\n"
				+ "				<h1>ONLINE UNIVERSITY YEAR BOOK SYSTEM</h1>\r\n"
				+ "                <h2>STUDENT LOGIN</h2>\r\n"
				+ "				<form method=\"post\" action=\"Student_login_servlet\">\r\n"
				+ "				\r\n"
				+ "				    <p>Username</p>\r\n"
				+ "                    <input type=\"text\" placeholder=\"Enter Username\" name=\"username\">\r\n"
				+ "				    <p>Password</p>\r\n"
				+ "                    <input type=\"password\" placeholder=\"Enter Password\" name=\"password\" >\r\n"
				+ "                    <input type=\"submit\" value=\"Sign In\">\r\n"
				+ "                    <p>Don't have an account? <a href='student_newregister_servlet'>Create a new account</a></p>\r\n"
				+ "\r\n"
				+ "				</form>\r\n"
				+ "			</div>\r\n"
				+ "		</body>\r\n"
				+ "</html>");
	}


}
