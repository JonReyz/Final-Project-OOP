import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class perfilUpdateServlet
 */
@WebServlet("/perfilUpdateServlet")
public class perfilUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public perfilUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbCode = request.getParameter("dbcode");
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties properties = new Properties();
		properties.put("user", "user");
		properties.put("password", "123");
		
		// Get a connection
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample;create=true", properties);
			System.out.println("Conectado");
			System.out.println (dbCode);
			Statement s = conn.createStatement();
			s.execute(dbCode);
			response.getWriter().write("1");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		response.getWriter().write("-1");
		System.out.println("bug");
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

}
