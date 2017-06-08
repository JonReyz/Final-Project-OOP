/**
 * 
 * @author JOAO PEDRO
 *
 */

import java.sql.*;
public class ConnectionDb {
	
	String driver = "jdbc:derby:./Data/database;create=true";
	Connection con;
	boolean start = false;
	
	/**
	 * Construtor da classe, quando � chamado cria a conex�o com o banco de dados
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
	
	/**
	 * Fun��o que � respons�vel por criar uma tabela no banco de dados
	 * @param sql Comando sql para a cria��o da tabela
	 * @param sql2 Comando em sql usado para verificar se a tabela existe (select * from X)
	 * @throws SQLException
	 */
	public void createTable(String sql,String sql2) throws SQLException{
		Statement s = con.createStatement();
		//Se a tabela n�o existe, ela � criada
		if(!ConnectionDb.tableExists(con,sql2)){
			 System.out.println (" . . . . creating table");
             s.execute(sql);
		}
		s.close();
	}
	
	
	/**
	 * Fun��o que recebe dois comandos sqls, um de inser��o e outro para a verifica��o da exist�ncia da tabela
	 * @param sql (insert ...)
	 * @param sql2 (select * from X)
	 * @throws SQLException
	 */
	public void insertTable(String sql, String sql2) throws SQLException{
		PreparedStatement psInsert;
		if(ConnectionDb.tableExists(con,sql2)){
			psInsert = con.prepareStatement(sql); 
			psInsert.executeUpdate();
			System.out.println("New row added to the table...");
			psInsert.close();
		}
	}
	
	/**
	 * Fun��o respons�vel por promover a dele��o de um registro em uma tabela qualquer
	 * @param table Recebe uma string com o nome da tabela
	 * @param condition Recebe uma string com o campo onde a condi��o deve ser respeitada
	 * @param name Identificador de qual registro deve ser apagado
	 */
	public void deleteTable(String table,String condition, String name){
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
            System.out.println("Erro ao fazer a dele��o da tabela");
            e.printStackTrace();   
        }

	}
	
	/**
	 * Fun��o respons�vel por executar um update em uma tabela no banco de dados
	 * @param sql Comando sql para executar o update
	 * @param sql2 Comando em sql para executar a verifica��o da exist�ncia de tabela (select * from X)
	 * @throws SQLException
	 */
	public void updateTable(String sql, String sql2) throws SQLException{
		PreparedStatement psInsert;
		if(ConnectionDb.tableExists(con,sql2)){
			psInsert = con.prepareStatement(sql); 
			psInsert.executeUpdate();
			psInsert.close();
			System.out.println("Altera��o realizada com sucesso");
		}
	}
	
	/**
	 * Fun��o recebe o nome da tabela, o numero de campos e printa todo o conteudo da mesma, se ela existir
	 * @param table
	 * @param fields
	 */
	public void getAllTable(String table, int fields){
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
	
	/**
	 * Fun��o para fechar a conex�o com o banco de dados
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException{
		con.close();
		System.out.println("Closing the database...");
	}
	/**
	 * Fun��o que verifica se a tabela j� est� criada no bando de dados
	 * @param con 
	 * @param sql Comando em sql que ir� fazer essa verifica��o (select * from X)
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
			System.out.println("Tabela n�o existente...");
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		
		String create = "create table teste123 (id int not null generated always as identity constraint use_pk primary key, name varchar(30) not null, passwd varchar(30) not null)";
		String ver = "select * from Guardians";
		String update="drop table Users";
		
		String insert1 = "insert into Users(login,passwd,type) values('admin','admin',0)";
		String insert2 = "insert into Users(login,passwd,type) values('ong','ong',1)";
		
		String insert3 = "insert into Guardians(name,email,login) values('Teste de cliente','teste@cliente.com',(select login from Users where login='admin'))";
		String insert4 = "insert into Ongs(email,adress,phone,cnpj,description,login) values('Ong@email','Rua da ong','32419262','123123123','Uma ong legal',(select login from Users where login='ong'))";
		String insert5 = "insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description) values('Pillow','Gato','F','0','Pequena','Curto','D�cil','asd@asd',3,'0',(select login from Users where login='ong'),'Uma gatinha fofa')";
		
		
		
		String createUsers ="create table Adoptions ("
				+"adp_id int not null generated always as identity (start with 0, increment by 1),"
				+"guardian varchar(32) not null,"
				+"animal int not null,"
				+"CONSTRAINT adp_pk PRIMARY KEY (adp_id),"
				+"CONSTRAINT adpU_fk FOREIGN KEY (guardian) REFERENCES Users(login),"
				+"CONSTRAINT adpA_fk FOREIGN KEY (animal) REFERENCES Animals(animal_id)"
				+")";
 
		
		
		ConnectionDb db = new ConnectionDb();
		
		String insert = "insert into teste123(name,passwd) values('Jo�o', '123456')";
		String table = "teste123";
		try {
			//db.createTable(createUsers, ver);
			//db.getAllTable(table, 3);
			//db.deleteTable(table,"name", "Jo�o");
			//db.insertTable(insert5, ver);
			db.getAllTable("Animals", 13);
			db.closeDB();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		
}
