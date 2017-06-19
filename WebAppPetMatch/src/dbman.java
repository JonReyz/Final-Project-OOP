import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class answerServlet
 */
@WebServlet("/dbman")
public class dbman extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public dbman() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbCode = request.getParameter("dbcode");
		String typ = request.getParameter("type");
		//response.getWriter().write(typ);
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
			Connection conn = DriverManager.getConnection("jdbc:derby://192.168.0.26:1527/sample;create=true", properties);
			System.out.println("Conectado");
			System.out.println (dbCode);
			Statement s = conn.createStatement();
			if(typ.equals("2")){
		        ResultSet re = s.executeQuery(dbCode);
		        ResultSetMetaData remd = re.getMetaData();
		        //s.execute(dbCode);
				String ret = "[";
		        //response.getWriter().write(Arrays.toString(re));
		        while (re.next()) {
		        	if (ret.length()>1)
		        		ret+=",";
		        	ret += "[";
		        	for (int i=1; i <= remd.getColumnCount(); i++){
		        		if (i!=1)
		        			ret +=",";
		        		ret += "\""+re.getString(i)+"\"";
		        	}
		        	ret += "]";
		        	//System.out.printf("%s %s %s\n", re.getString(1), re.getString(2), re.getString(3));
		        	//ret += "[\"" + re.getString(1) + "\"," + "\"" + re.getString(2) + "\"," + "\"" + re.getString(3) + "\"]";
		        	System.out.printf("%s\n", ret);
		        	
		        }
		        ret+="]";
		        response.getWriter().write(ret);
				s.close();
			}if(typ.equals("1")){
				s.execute(dbCode);
				response.getWriter().write(dbCode);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("bug");
			e.printStackTrace();
		}
		
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
