/**
 * 
 * @author JOAO PEDRO
 *
 */

import java.sql.*;
public class ConnectionDb {
	
	static String driver = "jdbc:derby:./Data/database;create=true";
	static Connection con;
	static boolean start = false;
	
	/**
	 * Construtor da classe, quando ï¿½ chamado cria a conexï¿½o com o banco de dados
	 */
	public ConnectionDb(){
		System.out.println("Trying to connect with the database...");
		try {
			this.con = DriverManager.getConnection(driver);
			System.out.println("Connected to database with sucess!!");
			this.start = true;
		} catch (SQLException e) {
			System.out.println("Failed to connect with the database!!");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void ConnectWithDatabase(){
		try {
			con = DriverManager.getConnection(driver);
			System.out.println("Connected to database with sucess!!");
			start = true;
		} catch (SQLException e) {
			System.out.println("Failed to connect with the database!!");
			e.printStackTrace();
		}
	}
	/**
	 * Funï¿½ï¿½o que ï¿½ responsï¿½vel por criar uma tabela no banco de dados
	 * @param sql Comando sql para a criaï¿½ï¿½o da tabela
	 * @param sql2 Comando em sql usado para verificar se a tabela existe (select * from X)
	 * @throws SQLException
	 */
	public static void  createTable(String sql,String sql2) throws SQLException{
		Statement s = con.createStatement();
		//Se a tabela nï¿½o existe, ela ï¿½ criada
		if(!ConnectionDb.tableExists(con,sql2)){
			 System.out.println (" . . . . creating table");
             s.execute(sql);
		} 
		s.close();
	}
	
	
	/**
	 * Funï¿½ï¿½o que recebe dois comandos sqls, um de inserï¿½ï¿½o e outro para a verificaï¿½ï¿½o da existï¿½ncia da tabela
	 * @param sql (insert ...)
	 * @param sql2 (select * from X)
	 * @throws SQLException
	 */
	public static void insertTable(String sql, String sql2) throws SQLException{
		PreparedStatement psInsert;
		if(ConnectionDb.tableExists(con,sql2)){
			psInsert = con.prepareStatement(sql); 
			psInsert.executeUpdate();
			System.out.println("New row added to the table...");
			psInsert.close();
		}
	}
	
	/**
	 * Funï¿½ï¿½o responsï¿½vel por promover a deleï¿½ï¿½o de um registro em uma tabela qualquer
	 * @param table Recebe uma string com o nome da tabela
	 * @param condition Recebe uma string com o campo onde a condiï¿½ï¿½o deve ser respeitada
	 * @param name Identificador de qual registro deve ser apagado
	 */
	public static void deleteTable(String table,String condition, String name){
		String d = "DELETE FROM " + table + " WHERE " + condition + " = '" +  name + "'";
		try {      
            PreparedStatement ps = con.prepareStatement(d);   
            //ps.setString(1, table);
           // ps.setString(2, condition);
            //ps.setString(3, name);
            ps.executeUpdate();
            ps.close();
		}
        catch (SQLException e){   
            System.out.println("Erro ao fazer a deleï¿½ï¿½o da tabela");
            e.printStackTrace();   
        }

	}
	
	/**
	 * Funï¿½ï¿½o responsï¿½vel por executar um update em uma tabela no banco de dados
	 * @param sql Comando sql para executar o update
	 * @param sql2 Comando em sql para executar a verificaï¿½ï¿½o da existï¿½ncia de tabela (select * from X)
	 * @throws SQLException
	 */
	public  static void updateTable(String sql, String sql2) throws SQLException{
		PreparedStatement psInsert;
		if(ConnectionDb.tableExists(con,sql2)){
			psInsert = con.prepareStatement(sql); 
			psInsert.executeUpdate();
			psInsert.close();
			System.out.println("Alteraï¿½ï¿½o realizada com sucesso");
		}
	}
	
	/**
	 * Funï¿½ï¿½o recebe o nome da tabela, o numero de campos e printa todo o conteudo da mesma, se ela existir
	 * @param table
	 * @param fields
	 */
	public static void getAllTable(String table, int fields){
		Statement s;
		try {
			s = con.createStatement();
			ResultSet result = s.executeQuery("select * from " + table);
			while (result.next()){
            	for(int i=1;i<=fields;i++){
            		System.out.print(result.getString(i) + " - " );
            	}
				System.out.println();
            }
			result.close();
			s.close();
		} catch (SQLException e) {
			System.out.println("Erro ao lista tabela");
			e.printStackTrace();
		}
		
	}
	
	public static User getUserDB(String name) throws SQLException{
		Statement s;
		String sql = "select * from Users where login='" + name +"'";
		System.out.println(sql);
		User user = null;
		try {
			s = con.createStatement();
			ResultSet result = s.executeQuery(sql);
			result.next();
			user = new User(result.getString(1),result.getString(2),Integer.parseInt(result.getString(3)));
			result.close();	
			s.close();	
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter usuï¿½rio");
			con.close();
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	/**
	 * Retorna os dados de um Guardian baseado no login do User
	 * @param u - user que contem o login utilizado para a busca no BD
	 * @return Guardian guardian - retorna um objeto Guardian para uso
	 * @throws SQLException
	 */
	public static Guardian getGuardianDB(User u) throws SQLException{
		Statement s;
		String sql = "select * from Guardians where login='" + u.getLogin() +"'";
		System.out.println(sql);
		Guardian guardian = null;
		try {
			s = con.createStatement();
			ResultSet result = s.executeQuery(sql);
			result.next();
			guardian = new Guardian(u,result.getString(2),result.getString(3)); ///(?) ta dando ruim aqui - Estado do cursor invalido
			result.close();	
			s.close();	
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter usuï¿½rio");
			con.close();
			e.printStackTrace();
		}
		
		return guardian;
	}
	
	/**
	 * Retorna os dados de um Ong baseado no login do User
	 * @param u - user que contem o login utilizado para a busca no BD
	 * @return Ong ong - retorna um objeto Ong para uso
	 * @throws SQLException
	 */
	public static Ong getOngDB(User u) throws SQLException{
		Statement s;
		String sql = "select * from Ongs where login='" + u.getLogin() +"'";
		System.out.println(sql);
		Ong ong = null;
		try {
			s = con.createStatement();
			ResultSet result = s.executeQuery(sql);
			result.next();
			ong = new Ong(u,result.getString(1),result.getString(2), result.getString(3), result.getString(4), result.getString(5));
			result.close();	
			s.close();	
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter usuï¿½rio");
			con.close();
			e.printStackTrace();
		}
		
		return ong;
	}
	
	/**
	 * Funï¿½ï¿½o para fechar a conexï¿½o com o banco de dados
	 * @throws SQLException
	 */
	public static void closeDB() throws SQLException{
		con.close();
		System.out.println("Closing the database...");
	}
	/**
	 * Funï¿½ï¿½o que verifica se a tabela jï¿½ estï¿½ criada no bando de dados
	 * @param con 
	 * @param sql Comando em sql que irï¿½ fazer essa verificaï¿½ï¿½o (select * from X)
	 * @return
	 */
	public static boolean tableExists(Connection con, String sql){
		try{
			Statement s = con.createStatement();
			s.execute(sql);
			s.close();
			System.out.println("Tabela existe");
			return true;
		} catch (SQLException sqle){
			String error = sqle.getSQLState();
			System.out.println("Tabela nï¿½o existente...");
			return false;
		}
	}
	
	
	public static boolean sqlExists(String sql){
		try{
			Statement s = con.createStatement();
			s.execute(sql);
			s.close();
			System.out.println("Comando encontrado");
			return true;
		} catch (SQLException sqle){
			String error = sqle.getSQLState();
			System.out.println("Comando nï¿½o existente...");
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		String create = "create table Adoptions ("
				+"adp_id int not null generated always as identity (start with 0, increment by 1),"
				+"guardian varchar(32) not null,"
				+"animal int not null,"
				+"CONSTRAINT adp_pk PRIMARY KEY (adp_id),"
				+"CONSTRAINT adpU_fk FOREIGN KEY (guardian) REFERENCES Users(login),"
				+"CONSTRAINT adpA_fk FOREIGN KEY (animal) REFERENCES Animals(animal_id)"
				+")";
		String ver = "select * from Adoptions";
		String update="drop table Animals";
		
		
		
		
		String insert1 = "insert into Users(login,passwd,type) values('admin','admin',0)";
		String insert2 = "insert into Users(login,passwd,type) values('ong','ong',1)";
		
		String insert3 = "insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Obitão','Cão','M','0','Grande','Grande','Brabo',3,'0',(select login from Users where login='ong'),'Obitão é brabo')";

		String insert4 = "insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Pillow','Gato','F','0','Pequena','Curto','Dócil',3,'0',(select login from Users where login='ong'),'Uma gatinha fofa')";
		String insert5 = "insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Obitinho','Cão','M','0','pequeno','pequeno','dócil',10,'0',(select login from Users where login='ong'),'heeey')";
		
		
		
		String createUsers ="create table Adoptions ("
				+"adp_id int not null generated always as identity (start with 0, increment by 1),"
				+"guardian varchar(32) not null,"
				+"animal int not null,"
				+"CONSTRAINT adp_pk PRIMARY KEY (adp_id),"
				+"CONSTRAINT adpU_fk FOREIGN KEY (guardian) REFERENCES Users(login),"
				+"CONSTRAINT adpA_fk FOREIGN KEY (animal) REFERENCES Animals(animal_id)"
				+")";
 
		
		
		ConnectionDb db = new ConnectionDb();
		
		//db.closeDB();
		
		String insert = "insert into teste123(name,passwd) values('Joï¿½o', '123456')";
		String table = "Animals";
		try {
			db.createTable(create, ver);
			//db.getAllTable(table, 3);
			//db.deleteTable(table,"name", "Joï¿½o");
			//db.insertTable(insert4, ver);
			//db.getAllTable("Animals", 13);
			db.closeDB();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		
}
