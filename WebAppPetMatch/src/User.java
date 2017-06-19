import java.io.IOException;
import java.sql.SQLException;

public class User {
	private String login; //login 
	private String passwd; //senha
	private int type; // se e um guardiao ou uma ong 
	
	/**
	 * Cadastra novo usuario
	 * @throws IOException 
	 */
	public User() throws IOException{
		//lembrar de verificar banco de dados assegurando que n existem logins repetidos
		System.out.println("Digite seu login:");
		login = EntradaTeclado.leString();
		System.out.println("Digite sua senha:");
		passwd = EntradaTeclado.leString();
		System.out.println("Voce deseja criar um perfil de ONG ou de pessoa fisica?");
		System.out.println("0 - ONG;" + "\n" + "1 - Pessoa fisica");
		type = EntradaTeclado.leInt();
	}
	
	/**
	 * Construtor alternativo para gerar um User a partir das informacoes do BD
	 * @param l - login do User
	 * @param p - senha do User
	 * @param t - tipo do User (ONG ou Guardian)
	 */
	public User(String l, String p, int t) {
		login = l;
		passwd = p;
		type = t;
	}

	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	
	
	public String getPasswd(){
		return this.passwd;
	}
	
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	
	
	public int getType(){
		return this.type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	/**
	 * Fun��o respons�vel por cadastrar o objeto no banco de dados
	 */
	public void putInDatabase(){
		
		String sql = "insert into Users(name,passwd,type) values('"+this.login+"','" + this.passwd +"','"+this.type+"')";
		String ver = "select * from Users";
		// a considerar
		ConnectionDb.ConnectWithDatabase();
		//talvez fazer uma verifica��o melhor
		try {
			ConnectionDb.insertTable(sql, ver);
		} catch (SQLException e) {
			System.out.println("Usu�rio j� existe");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}



