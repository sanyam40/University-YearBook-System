package com.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Teacher_Update_Servlet
 */
public class Teacher_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_Update_Servlet() {
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
			update(request,response);
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
			edit_info(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void edit_info(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String idd=request.getQueryString();
		String ids[]=idd.split("=");
		String username=ids[1];
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
        
        String usernamee=request.getParameter("username");
		String password=request.getParameter("password");
		
		PreparedStatement ps=con.prepareStatement("update student_login set username=?,password=? where username = ?");
		
		ps.setString(1, usernamee);
		ps.setString(2, password);
		ps.setString(3, username);
		
		
		ps.executeUpdate();
		try {
			response.sendRedirect("/EWA_FINAL_PROJECT/Teacher_login_servlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		String idd=request.getQueryString();
		String[] ids=idd.split("=");
        String username=ids[1];
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
        
        String query = "select * from student_login where username = ?";
        
        PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, username);
		ResultSet rs = pst.executeQuery();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		while(rs.next()) {
			out.write("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
					+ "  <title>UPDATE DETAILS</title>\r\n"
					+ "  <link rel=\"stylesheet\" href=\"student_login_css.css\">\r\n"
					+ "</head>\r\n"
					+ " \r\n"
					+ "<body>\r\n"
					+ "<h2>UPDATE STUDENT LOGIN DETAILS</h2>\r\n"
					+"<form action='/EWA_FINAL_PROJECT/Teacher_Update_Servlet?id="+username+"' method='post'>"
					+ "  <fieldset>");  
            out.print("<table>"); 
			out.print("<legend>UPDTE DATA</legend>"); 
            out.print("<tr><td>username:</td><td><input type='text' name='username' value='"+rs.getString(1)+"'/></td></tr>");     
            out.print("<tr><td>password:</td><td><input type='password' name='password' value='"+rs.getString(2)+"'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save'/></td></tr>");  
            out.print("</fieldset>");
            out.print("</table>");  
            out.print("</form>");  
            out.print("</body>\r\n"
            		+ "</html>");
		}
	}


}
