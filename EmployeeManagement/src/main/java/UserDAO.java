import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
//	private static String jdbcUrl="jdbc:mysql://localhost:3306/arundb";
//	private static String jdbcUserName= "root";
//	private static String jdbcPassword= "Arun@1103";
	
	private static final String SELECT_ALL_USERS = "select * from user";
	private static final String INSERT_NEW_USER = "insert into user"+"(name,password,email,country)" +"values (?,?,?,?)";
	private static final String UPDATE_USERS_SQL = "update user set name=?, password=?, email=?, country=? where id=?";
	private static final String DELETE_USERS_SQL = "delete from user where id=?";
	
	public UserDAO() {}
	protected static Connection getConnection() {
		Connection con = null;
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arundb","root","Arun@1103");
		}catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
//            catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return con;
		
	}
	
	public static List<User> selectAllUsers(){
	
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> list = new ArrayList<>();
		
		// Step 1: Establishing a Connection
		try {
			Connection con = UserDAO.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_USERS);
			System.out.println(pstmt);
			// Step 3: Execute the query or update query
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String country = rs.getString("country");
                list.add(new User(id, name,password, email, country));
			}
			con.close();
			}catch(SQLException e) {}
		return list;	 		
	}
	
    public static User getEmployeeById(int id){  
        
    	User e = null;
        try{  
        	e=new User();  
            Connection con=UserDAO.getConnection();  
            PreparedStatement pstmt=con.prepareStatement("select * from user where id=?");  
            pstmt.setInt(1,id);  
            ResultSet rs=pstmt.executeQuery();  
            if(rs.next()){  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return e;  
    }
	public static int insertUser(User e)throws SQLException {
		int status=0;
		try {
		Connection con =UserDAO.getConnection();
		PreparedStatement pstmt = con.prepareStatement(INSERT_NEW_USER);
			pstmt.setString(1,e.getName());
			pstmt.setString(2,e.getPassword());
			pstmt.setString(3, e.getEmail());
			pstmt.setString(4, e.getCountry());
			status=pstmt.executeUpdate();
			con.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return status;
	}
	public static int updateUser(User e) throws SQLException{
		int status = 0;
		try
		{	Connection con=UserDAO.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE_USERS_SQL);
			
			 pstmt.setString(1, e.getName());  
	         pstmt.setString(2,e.getPassword());  
	         pstmt.setString(3,e.getEmail());  
	         pstmt.setString(4,e.getCountry()); 
	         pstmt.setInt(5, e.getId());
	           
	              
	            status=pstmt.executeUpdate();  
	              
	            con.close();  
		}catch(SQLException s) {
			s.printStackTrace();
			}
		return status;
	}
	
	public static boolean deleteUser(int id) throws SQLException{
		boolean rowsDeleted;
		try (Connection con = UserDAO.getConnection();PreparedStatement pstmt = con.prepareStatement(DELETE_USERS_SQL);)
		{
			pstmt.setInt(1, id);
			rowsDeleted = pstmt.executeUpdate()>0;
			con.close();
		}
		return rowsDeleted;
	}
}
