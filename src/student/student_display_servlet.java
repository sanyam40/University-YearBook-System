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
 * Servlet implementation class student_display_servlet
 */
public class student_display_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public student_display_servlet() {
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		out.print("<a href='index.jsp'>TAKE ME TO HOME PAGE</a>");
		out.print("<br>");
		out.print("<table border='1' width'100'");
        out.write("<tr><th>SNO</th><th>Id</th><th>Acheived_By</th><th>Name</th><th>Task</th><th>Achievements</th><th>Year</th></tr>");
		
		while(rs.next()) {
			int sno=rs.getInt("sno");
			int id=rs.getInt("id");
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
		    		   +"</tr>");
		}
	}


}
