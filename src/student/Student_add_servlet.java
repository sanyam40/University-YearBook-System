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
 * Servlet implementation class Student_add_servlet
 */
public class Student_add_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_add_servlet() {
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
			add(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
		
		String sno_t=request.getParameter("sno");
		int sno=Integer.parseInt(sno_t);
		String idd=request.getParameter("id");
		int id=Integer.parseInt(idd);
		String Acheived_By=request.getParameter("Acheived_By");
		String Name=request.getParameter("Name");
		String Task=request.getParameter("Task");
		String Achievements=request.getParameter("Achievements");
		String Yearr=request.getParameter("Year");
		int year=Integer.parseInt(Yearr);
		
		String query="insert into main_data(sno,id,Acheived_by,Name,Task,Achievements,Year) values(?,?,?,?,?,?,?)";
        PreparedStatement stmt=con.prepareStatement(query);
        
        stmt.setInt(1,sno);
        stmt.setInt(2, id);
        stmt.setString(3,Acheived_By);
        stmt.setString(4,Name);
        stmt.setString(5,Task);
        stmt.setString(6,Achievements);
        stmt.setInt(7,year);
        
        stmt.executeUpdate();
        response.setContentType("text/html");
		PrintWriter out=response.getWriter();
    
        try {
			response.sendRedirect("/EWA_FINAL_PROJECT/student_display_servlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
    
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "  <title>NEW DATA</title>\r\n"
				+ "  <link rel=\"stylesheet\" href=\"student_login_css.css\">\r\n"
				+ "</head>\r\n"
				+ " \r\n"
				+ "<body>\r\n"
				+ "<h2>NEW ACHEIVMENTS</h2>\r\n"
				+ "<form method=\"post\" action=\"Student_add_servlet\">\r\n"
				+ "  <fieldset>\r\n"
				+ "    <legend>ADD NEW DATA</legend>\r\n"
				+ "    sno : <input type=\"text\" name=\"sno\" /><br /><br />\r\n"
				+ "    id : <input type=\"text\" name=\"id\" /><br /><br />\r\n"
				+ "    Acheived_By : <input type=\"text\" name=\"Acheived_By\" /><br /><br />\r\n"
				+ "    Name : <input type=\"text\" name=\"Name\" /><br /><br />\r\n"
				+ "    Task : <input type=\"text\" name=\"Task\" /><br /><br />\r\n"
				+ "    Achievements : <input type=\"text\" name=\"Achievements\" /><br /><br />\r\n"
				+ "    Year : <input type=\"text\" name=\"Year\" /><br /><br />\r\n"
				+ "  </fieldset>\r\n"
				+ "  <input type=\"hidden\" name=\"secret\" value=\"888\" />\r\n"
				+ "  <input type=\"submit\" value=\"SEND\" />\r\n"
				+ "  <input type=\"reset\" value=\"CLEAR\" />\r\n"
				+ "</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
}
