

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/EditServlet2")
/**
 * Servlet implementation class EditServlet2
 */
public class EditServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		
		String name=request.getParameter("name");  
        String password=request.getParameter("password");  
        String email=request.getParameter("email");  
        String country=request.getParameter("country");
        
        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setPassword(password);
        u.setEmail(email);
        u.setCountry(country);
        
        try {
			int status = UserDAO.updateUser(u);
			out.println("EditServlet2 entered");
			 if(status>0){  
		            response.sendRedirect("ViewServlet");  
		        }else{  
		            out.println("Sorry! unable to update record");  
		        }  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.close();

	}

}
