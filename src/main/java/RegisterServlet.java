import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String name = request.getParameter("name");
	    String address = request.getParameter("address");

	    // Database connection parameters
	    String jdbcURL = "jdbc:mysql://localhost:3306/project_part2_db";
	    String dbUser = "root";
	    String dbPassword = "";

	    Connection connection = null;
	    PreparedStatement loginStatement = null;
	    PreparedStatement userInfoStatement = null;
	    
	    try {
            // Step 1: Establish connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Step 2: Insert username and password into the login table
            String insertLoginSQL = "INSERT INTO login (username, password, admin, active) VALUES (?, ?, ?, ?)";
            loginStatement = connection.prepareStatement(insertLoginSQL, Statement.RETURN_GENERATED_KEYS);
            loginStatement.setString(1, username);
            loginStatement.setString(2, password);
            loginStatement.setBoolean(3, false);
            loginStatement.setBoolean(4, true);
            
            // Execute the insert statement
            int affectedRows = loginStatement.executeUpdate();
            
            if (affectedRows == 0) {
                request.setAttribute("errorMessage", "Creating user failed, no rows affected.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            }

            // Step 3: Retrieve the generated ID from the login table
            ResultSet generatedKeys = loginStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);  // Retrieve generated ID from the login table

                // Step 4: Insert the user info (name, address) into the userinformation table
                String insertUserInfoSQL = "INSERT INTO userinformation (id, name, address) VALUES (?, ?, ?)";
                userInfoStatement = connection.prepareStatement(insertUserInfoSQL);
                userInfoStatement.setInt(1, userId);
                userInfoStatement.setString(2, name);
                userInfoStatement.setString(3, address);

                // Execute the insert for user information
                userInfoStatement.executeUpdate();
            }

            // Step 5: Send response to the client (Redirect to login page after successful registration)
            request.setAttribute("successMessage", "You have successfully registered your account!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("login.jsp");  // Redirect to login page

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error during registration:" + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        } finally {
            // Clean up resources
            try {
                if (loginStatement != null) {
                    loginStatement.close();
                }
                if (userInfoStatement != null) {
                    userInfoStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
