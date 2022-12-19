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
		out.write("<h3>ACHEIVMENTS LIST</h3>");
		
		String query="select * from main_data";
		PreparedStatement pst = con.prepareStatement(query);
		
		ResultSet rs = pst.executeQuery();
		out.print("<table border='1' width'100'");
        out.write("<tr><th>SNO</th><th>Id</th><th>Acheived_By</th><th>Name</th><th>Task</th><th>Achievements</th><th>Year</th><th>Update</th><th>Delete</th></tr>");
		
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
		out.print("<a href='Student_add_servlet'>ADD NEW DATA</a>");
	}
	
	static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "  <title>YEAR BOOK SYSTEM - LOGIN</title>\r\n"
				+ "  <link rel=\"stylesheet\" href=\"student_login_css.css\">\r\n"
				+ "</head>\r\n"
				+ " \r\n"
				+ "<body>\r\n"
				+ "<div class=\"student_login_form\">\r\n"
				+ "<h2>Online University Yearbook System Student Login Page</h2>\r\n"
				+ "<form method=\"post\" action=\"Student_login_servlet\">\r\n"
				+ "  <fieldset>\r\n"
				+ "    username : <input type=\"text\" name=\"username\" /><br /><br />\r\n"
				+ "    password : <input type=\"password\" name=\"password\" /><br /><br />\r\n"
				+ "  </fieldset>\r\n"
				+ "  <input type=\"hidden\" name=\"secret\" value=\"888\" />\r\n"
				+ "  <a href='student_newregister_servlet'>New Register</a>\r\n"
				+ "  <input type=\"submit\" value=\"SEND\" />\r\n"
				+ "  <input type=\"reset\" value=\"CLEAR\" />\r\n"
				+ "</form>\r\n"
				+ "</div>\r\n"
				+ "</body>\r\n"
				+ "</html>");	}


}
