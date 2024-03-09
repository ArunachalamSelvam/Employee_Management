

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

/**
 * Servlet implementation class loginServlet
 */
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	 public void init() {
	        userDAO = new UserDAO();
	    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String successMessage = (String) request.getAttribute("successMessage");
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		 if (successMessage != null) {
	            out.println("<p style='color: green;'>" + successMessage + "</p>");
		 }
		out.println("<button type='submit' style='background-color:#4caf50;border:none;padding: 10px;border-radius:4px' onmouseover= 'this.style.backgroundColor='#45a049' onmouseout='this.style.backgroundColor='#4caf50''>");
		out.println("<a href='login.html' style='color: white; text-decoration:none;'>Add New Employee</a>");  
		out.println("</button>");
        out.println("<h1>Employees List</h1>");  
          
        List<User> list=UserDAO.selectAllUsers();  
          
        out.print("<table border='1' width='100%' style='border-collapse:collapse;'");  
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th> <th>Edit</th><th>Delete</th></tr>");  
        for(User e:list){  
			out.print("<tr><td name='id'>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getPassword() + "</td><td>"
					+ e.getEmail() + "</td><td>" + e.getCountry() + "</td><td><a href='EditServlet?id=" + e.getId()
					+ "'>edit</a></td>  <td><a href='DeleteServlet?id=" + e.getId() + "'>delete</a></td></tr>");
        }  
        out.print("</table>");  
          
        out.close();
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
