

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
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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

			String sql2 = "SELECT type FROM Users WHERE login = '" + userInput + "' AND passwd='" + passInput + "'" ;
			Statement s = conn.createStatement();
			ResultSet re = s.executeQuery(sql2);
            if (re.next()){
            	if(re.getInt(1) == 0){
            		Statement s2 = conn.createStatement();
            		String sql = "SELECT * FROM Guardians WHERE login = '" + userInput + "'";
            		ResultSet re2 = s2.executeQuery(sql);
            		String ret = "[";
            		ResultSetMetaData remd = re2.getMetaData();
            		while (re2.next()) {
            			if (ret.length()>1)
         	        		ret+=",";
         	        	ret += "[";
         	        	for (int i=1; i <= remd.getColumnCount(); i++){
         	        		if (i!=1)
         	        			ret +=",";
         	        		ret += "\""+re2.getString(i)+"\"";
         	        	}
         	        	ret += "]";
            		}
            		ret += "]";
                	response.getWriter().write(ret);
            	}
            }
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
