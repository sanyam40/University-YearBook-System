package com.student;

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
 * Servlet implementation class Student_Update_servlet
 */
public class Student_Update_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_Update_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String iddd=ids[1];
		int id=Integer.parseInt(iddd);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
        
        String sno_t=request.getParameter("sno");
		int sno=Integer.parseInt(sno_t);
		//String idd=request.getParameter("id");
		//int id=Integer.parseInt(idd);
		String Acheived_By=request.getParameter("Acheived_By");
		String Name=request.getParameter("Name");
		String Task=request.getParameter("Task");
		String Achievements=request.getParameter("Achievements");
		String Yearr=request.getParameter("Year");
		int year=Integer.parseInt(Yearr);
		
		PreparedStatement ps=con.prepareStatement("update main_data set sno=?,Acheived_By=?,Name=?,Task=?,Achievements=?,year=? where id = ?");
		ps.setInt(1, sno);
		ps.setString(2, Acheived_By);
		ps.setString(3, Name);
		ps.setString(4, Task);
		ps.setString(5, Achievements);
		ps.setInt(6, year);
		ps.setInt(7, id);
		
		ps.executeUpdate();
		try {
			response.sendRedirect("/EWA_FINAL_PROJECT/student_display_servlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		String idd=request.getQueryString();
		String[] ids=idd.split("=");
        String iddd=ids[1];
		int id=Integer.parseInt(iddd);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa_project","root","0721");
        
        String query = "select * from main_data where id = ?";
        
        PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		while(rs.next()) {
			out.write("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
					+ "  <title>NEW DATA</title>\r\n"
					+ "  <link rel=\"stylesheet\" href=\"student_login_css.css\">\r\n"
					+ "</head>\r\n"
					+ " \r\n"
					+ "<body>\r\n"
					+ "<h2>UPDTE ACHEIVMENTS</h2>\r\n"
					+"<form action='/EWA_FINAL_PROJECT/Student_Update_servlet?id="+id+"' method='post'>"
					+ "  <fieldset>");  
            out.print("<table>"); 
			out.print("<legend>UPDTE DATA</legend>");
            out.print("<tr><td>id:</td><td><input type='text' name='id' value='"+id+"' disabled/></td></tr>");    
            out.print("<tr><td>sno:</td><td><input type='text' name='sno' value='"+rs.getString(1)+"'/></td></tr>");     
            out.print("<tr><td>Acheived_by:</td><td><input type='text' name='Acheived_By' value='"+rs.getString(3)+"'/></td></tr>");
            out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+rs.getString(4)+"'/></td></tr>");
            out.print("<tr><td>Task:</td><td><input type='text' name='Task' value='"+rs.getString(5)+"'/></td></tr>");
            out.print("<tr><td>Achievements:</td><td><input type='text' name='Achievements' value='"+rs.getString(6)+"'/></td></tr>");
            out.print("<tr><td>Year:</td><td><input type='text' name='Year' value='"+rs.getString(7)+"'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save'/></td></tr>");  
            out.print("</fieldset>");
            out.print("</table>");  
            out.print("</form>");  
            out.print("</body>\r\n"
            		+ "</html>");
		}
	}


}
