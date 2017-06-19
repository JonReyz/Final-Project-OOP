

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
			Connection conn = DriverManager.getConnection("jdbc:derby://192.168.0.26:1527/sample;create=true", properties);
			System.out.println("Conectado");
			System.out.println (dbCode);
			Statement s = conn.createStatement();
		    ResultSet re = s.executeQuery(dbCode);
		    ResultSetMetaData remd = re.getMetaData();
		    String ret = "[";
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
		       	System.out.printf("%s\n", ret);  	
		    }
		    ret+="]";
		    response.getWriter().write(ret);
			s.close();
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
