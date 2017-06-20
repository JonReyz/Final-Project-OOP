import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class answerServlet
 */
@WebServlet("/testServ")
public class testServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public testServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String data = "Hello World";
		String userInput = request.getParameter("usu");
		String passInput = request.getParameter("pass");
		// Set user and password properties
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
			System.out.println("foi?");
			String sql  = "create table Ongs ("
					+"ong_id int not null generated always as identity (start with 0, increment by 1),"
					+"name varchar(32) not null"
					+")";
			String sql2 = "select * from Ongs" ;
			Statement s = conn.createStatement();
			//Se a tabela n�o existe, ela � criada
			if(!ConnectionDb.tableExists(conn,sql2)){
				 System.out.println (" . . . . creating table");
	             s.execute(sql);
			} 
			s.close();
			try {
				s = conn.createStatement();
				ResultSet result = s.executeQuery("select * from Ongs");
				while (result.next()){
	            	for(int i=1;i<=1;i++){
	            		System.out.print(result.getString(i) + " - " );
	            	}
					System.out.println("aa");
	            }
				result.close();
				s.close();
			} catch (SQLException e) {
				System.out.println("Erro ao lista tabela");
				e.printStackTrace();
			}
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
		PreparedStatement psInsert;
		
		//response.setContentType("text/plain");
		//response.setCharacterEncoding("UTF-8");/
		/**
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String dbURL = "jdbc:derby://localhost:1527/sample;create=true";
		String user = "user";
		String password = "123";
		System.out.println("Trying to connect to db...");
		Connection conn;
		try {
			conn = DriverManager.getConnection(dbURL, user, password);
			if (conn != null) {
				System.out.println("Connected to database #1");
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro");
		}
		response.getWriter().write(data + " PQP " + userInput + passInput);
		/*
		User user = null;
		
		//ConnectionDb.closeDB();
		ConnectionDb.ConnectWithDatabase();
		ConnectionDb.getAllTable("Users", 3);
		if (user == null){
			try {
				user = verificationLogin(userInput,passInput);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user == null) response.getWriter().write("Usu�rio ou senha incorreta");
			else response.getWriter().write("Tipo do usuario: " + user.getType());
		}*/
	}
/*
	public static User verificationLogin(String login, String passwd) throws SQLException{
		String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
		User user;
		if(ConnectionDb.sqlExists(sql)){
			user = ConnectionDb.getUserDB(login);
		}
		else return null;
		
		return user;
	}*/
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
