
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = new ArrayList<>();

        // JDBC variables
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Check the database to validate the credentials
	    String jdbcURL = "jdbc:mysql://localhost:3306/project_part2_db";
	    String dbUser = "root";
	    String dbPassword = "";

        String sql = "SELECT * FROM login";

		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            stmt = conn.prepareStatement(sql);
            
			rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAdmin(rs.getBoolean("admin"));
				user.setActive(rs.getBoolean("active"));
				userList.add(user);

			}
			// Set user list as a request attribute and forward to admin.jsp
			request.setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException  e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jdbcURL = "jdbc:mysql://localhost:3306/project_part2_db";
		String dbUser = "root";
		String dbPassword = "";

		Connection conn = null;
		PreparedStatement stmt = null;
		String message = "Users updated successfully!";

		try {
			// Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

			// Query to update admin and active columns
			String sql = "UPDATE login SET admin = ?, active = ? WHERE id = ?";
			stmt = conn.prepareStatement(sql);

			// Process each user from the request
			for (String paramName : request.getParameterMap().keySet()) {
				
				if (paramName.startsWith("id_")) {
	                String id = request.getParameter(paramName);
	                String adminParam = "admin_" + id;
	                String activeParam = "active_" + id;

	                boolean admin = (Integer.parseInt(request.getParameter(adminParam)) >0);
	                boolean active = (Integer.parseInt(request.getParameter(activeParam)) >0);

					stmt.setBoolean(1, admin);
					stmt.setBoolean(2, active);
					stmt.setString(3, id);
					stmt.addBatch();
					

				    System.out.println("User fetched: " + id + " admin "+request.getParameter(adminParam) + " active " +request.getParameter(activeParam));
	            }
			}

			// Execute batch update
			stmt.executeBatch();
			request.setAttribute("updateMessage", message);
		    doGet(request, response);
		    
		} catch (SQLException  | ClassNotFoundException  e) {
			e.printStackTrace();
	        message = "An error occurred while updating users.";
	        request.setAttribute("updateMessage", message);
	        doGet(request, response);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
