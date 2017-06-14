import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class answerServlet
 */
@WebServlet("/answerServlet")
public class answerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public answerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String data = "Hello World!";
		String userInput = request.getParameter("usu");
		String passInput = request.getParameter("pass");
		//response.setContentType("text/plain");
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().write(data + " e " + userInput + passInput);
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
