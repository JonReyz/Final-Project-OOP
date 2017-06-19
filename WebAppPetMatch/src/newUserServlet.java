

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newUserServlet
 */
@WebServlet("/newUserServlet")
public class newUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userInput = request.getParameter("usu");
		String passInput = request.getParameter("pass");
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
			Connection conn = DriverManager.getConnection("jdbc:derby://192.168.0.26:1527/sample;create=true", properties);

			//insert into Users(login,passwd,type) values('admin','admin',0);
			String sql2 = "INSERT INTO Users(login,passwd,type) VALUES ('" + userInput + "','" + passInput + "',0)" ;
			Statement s = conn.createStatement();
			s.execute(sql2);
			sql2 = "INSERT INTO Guardians(name,email,login) VALUES ('Sem Nome','email@email.com', '" + userInput + "')";
			s = conn.createStatement();
			s.execute(sql2);
         
			s.close();
			//Statement s = conn.createStatement();
			//Se a tabela n�o existe, ela � criada
			//if(!ConnectionDb.tableExists(conn,"tst")){
			//	 System.out.println (" . . . . creating table");
	         //    s.execute(sql);
			//} 
			//s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
