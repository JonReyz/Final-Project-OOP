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
	 * Construtor da classe, quando é chamado cria a conexão com o banco de dados
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
	 * Função que é responsável por criar uma tabela no banco de dados
	 * @param sql Comando sql para a criação da tabela
	 * @param sql2 Comando em sql usado para verificar se a tabela existe (select * from X)
	 * @throws SQLException
	 */
	public void createTable(String sql,String sql2) throws SQLException{
		Statement s = con.createStatement();
		//Se a tabela não existe, ela é criada
		if(!ConnectionDb.tableExists(con,sql2)){
			 System.out.println (" . . . . creating table");
             s.execute(sql);
		}
		s.close();
	}
	
	
	/**
	 * Função que recebe dois comandos sqls, um de inserção e outro para a verificação da existência da tabela
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
	 * Função responsável por promover a deleção de um registro em uma tabela qualquer
	 * @param table Recebe uma string com o nome da tabela
	 * @param condition Recebe uma string com o campo onde a condição deve ser respeitada
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
            System.out.println("Erro ao fazer a deleção da tabela");
            e.printStackTrace();   
        }

	}
	
	/**
	 * Função responsável por executar um update em uma tabela no banco de dados
	 * @param sql Comando sql para executar o update
	 * @param sql2 Comando em sql para executar a verificação da existência de tabela (select * from X)
	 * @throws SQLException
	 */
	public void updateTable(String sql, String sql2) throws SQLException{
		PreparedStatement psInsert;
		if(ConnectionDb.tableExists(con,sql2)){
			psInsert = con.prepareStatement(sql); 
			psInsert.executeUpdate();
			psInsert.close();
			System.out.println("Alteração realizada com sucesso");
		}
	}
	
	/**
	 * Função recebe o nome da tabela, o numero de campos e printa todo o conteudo da mesma, se ela existir
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
	 * Função para fechar a conexão com o banco de dados
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException{
		con.close();
		System.out.println("Closing the database...");
	}
	/**
	 * Função que verifica se a tabela já está criada no bando de dados
	 * @param con 
	 * @param sql Comando em sql que irá fazer essa verificação (select * from X)
	 * @return
	 */
	public static boolean tableExists(Connection con, String sql){
		try{
			Statement s = con.createStatement();
			s.execute(sql);
			s.close();
		} catch (SQLException sqle){
			String error = sqle.getSQLState();
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		
		String create = "create table teste123 (id int not null generated always as identity constraint use_pk primary key, name varchar(30) not null, passwd varchar(30) not null)";
		String ver = "select * from teste123";
		String update="update teste123 set passwd = '2333' where name ='Testão'";
		
		ConnectionDb db = new ConnectionDb();
		
		
		String insert = "insert into teste123(name,passwd) values('João', '123456')";
		String table = "teste123";
		try {
			db.createTable(create, ver);
			//db.insertTable(insert, ver);
			db.getAllTable(table, 3);
			//db.deleteTable(table,"name", "João");
			db.updateTable(update, ver);
			db.getAllTable(table, 3);
			db.closeDB();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		
}
