

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class animalsDbServlet
 */
@WebServlet("/animalsDbServlet")
public class animalsDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public animalsDbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbCode = request.getParameter("dbcode");
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
			System.out.println("Conectado");
			System.out.println (dbCode);
			Statement s = conn.createStatement();
		    ResultSet re = s.executeQuery(dbCode);
		    ResultSetMetaData remd = re.getMetaData();
		    re.next();
		    String login = re.getString(12);
		    System.out.println(login);
		    Statement s2 = conn.createStatement();
		    ResultSet re2 = s2.executeQuery("SELECT email FROM Guardians WHERE login = '" + login + "'");
		    re2.next();
		    String ret = "[";//11
		    do{
		       	if (ret.length()>1)
		       		ret+=",";
		       	ret += "[";
		       	for (int i=1; i <= remd.getColumnCount(); i++){
		       		if (i!=1)
		       			ret +=",";
		       		if (i==9)
		       			ret += "\""+re2.getString(1)+"\"";
		       		else
		       			ret += "\""+re.getString(i)+"\"";
		       	}
		       	ret += "]";
		       	System.out.printf("%s\n", ret);  	
		    }while (re.next());
		    ret+="]";
		    response.getWriter().write(ret);
			s.close();
			s2.close();
		}catch (SQLException e) {
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
