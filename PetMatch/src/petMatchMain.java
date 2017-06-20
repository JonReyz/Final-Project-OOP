import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.derby.iapi.error.StandardException;


public class petMatchMain {

	/**
	 * Imprime o menu de um usuario do tipo Ong
	 * @param user - dados da ONG
	 * @return int op - operacao para conduzir a continuacao ou nao do programa
	 * @throws IOException
	 * @throws SQLException
	 * @throws StandardException 
	 */
	public static int printMenuOng(Ong user) throws IOException, SQLException, StandardException{
		int op = -1;
		
				
		user.printAnimaisCadastrados();
		System.out.println("Para atualizar as informacoes sobre um animal digite 0;");
		System.out.println("Para apagar as informacoes sobre um animal digite 1;");
		System.out.println("Para cadastrar as informacoes sobre um novo animal digite 2;");
		System.out.println("Para sair digite 3.");
		op = EntradaTeclado.leInt();
		
		while(op != 3){
			switch (op){
			case 0:
				//atualiza as informacoes de certo animal
				System.out.println("Baseado na lista de seus animais, digite o numero do animal que deseja atualizar");
				user.atualizaAnimalCadastrado(EntradaTeclado.leInt());
				break;
			case 1:
				System.out.println("Baseado na lista de seus animais, digite o numero do animal que deseja apagar");
				user.apagaAnimalCadastrado(EntradaTeclado.leInt());
				break;
			case 2:
				Animal animal = new Animal(user);
				animal.putInDatabase();
				user.addAnimalCadastrado(animal);
				break;
				
			}
			
			
			
			user.printAnimaisCadastrados();
			System.out.println("Para atualizar as informacoes sobre um animal digite 0;");
			System.out.println("Para apagar as informacoes sobre um animal digite 1;");
			System.out.println("Para cadastrar as informacoes sobre um novo animal digite 2;");
			System.out.println("Para sair digite 3.");
			op  = EntradaTeclado.leInt();
		}
		
		return op;
		
	}
	
	/**
	 * Imprime o menu principal para um usuario Guardian (pessoa fisica)
	 * @param user - Dados do Guardian
	 * @return int op - operacao para conduzir a continuacao ou nao do programa
	 * @throws IOException
	 * @throws SQLException
	 * @throws StandardException 
	 */
	public static int printMenuGuardianMain(Guardian user) throws IOException, SQLException, StandardException{
		int op = -1;
		
		System.out.println("Para gerenciar sua lista de animais interessantes digite 0;");
		System.out.println("Para procurar animais utilizando filtros digite 1;");
		System.out.println("Para gerenciar os animais pelos qual eh responsavel digite 2;");
		op = EntradaTeclado.leInt();
		
		switch(op){
			case 0:
				op = printMenuGuardianInteresses(user);
				break;
			case 1:
				busca_filtros(user, 12);//12 - numero de campos da classe Animal
				
				break;
			case 2:
				op = printMenuGuardianCadastrados(user);
				break;
		}
		
		return op;
	}
	
	/**
	 * Imprime o menu para gerenciamento das adocoes pelo qual o Guardian eh responsavel
	 * @param user - Dados do Guardian
	 * @return int op - operacao para conduzir a continuacao ou nao do programa
	 * @throws IOException
	 * @throws SQLException
	 * @throws StandardException 
	 */
	public static int printMenuGuardianCadastrados(Guardian user) throws IOException, SQLException, StandardException{
		int op = -1;
		
		
		user.printAnimaisCadastrados();
		System.out.println("Para atualizar as informacoes sobre um animal digite 0;");
		System.out.println("Para apagar as informacoes sobre um animal digite 1;");
		System.out.println("Para cadastrar as informacoes sobre um novo animal digite 2;");
		System.out.println("Para sair digite 3.");
		op = EntradaTeclado.leInt();
		
		while(op != 3){
			switch (op){
			case 0:
				//atualiza as informacoes de certo animal
				System.out.println("Baseado na lista de seus animais, digite o numero do animal que deseja atualizar");
				user.atualizaAnimalCadastrado(EntradaTeclado.leInt());
				break;
			case 1:
				System.out.println("Baseado na lista de seus animais, digite o numero do animal que deseja apagar");
				user.apagaAnimalCadastrado(EntradaTeclado.leInt());
				break;
			case 2:
				Animal animal = new Animal(user);
				animal.putInDatabase();
				user.addAnimalCadastrado(animal);
				break;
				
			}
			
			
			
			
			System.out.println("Para atualizar as informacoes sobre um animal digite 0;");
			System.out.println("Para apagar as informacoes sobre um animal digite 1;");
			System.out.println("Para cadastrar as informacoes sobre um novo animal digite 2;");
			System.out.println("Para sair digite 3.");
			op  = EntradaTeclado.leInt();
		}
		
		return op;
		
	}
	
	/**
	 * Gerador de um vetor de Strings contendo os filtros para pesquisa
	 * @return String[] filters - vetor de filtros preenchido
	 * @throws IOException
	 */
	public static String[] gera_filtros() throws IOException{
		String[] filters = new String[5];
		
		System.out.println("Tipo do animal desejado (cao, gato...): ");
		filters[0] = EntradaTeclado.leString();
		
		System.out.println("Sexo do animal desejado (macho ou femea): ");
		filters[1] = EntradaTeclado.leString();
		
		System.out.println("Porte do animal desejado (pequeno, medio ou grande): ");
		filters[2] = EntradaTeclado.leString();
		
		System.out.println("Pêlo do animal desejado (curto, medio ou longo):");
		filters[3] = EntradaTeclado.leString();
		
		System.out.println("Temperamento do animal desejado (calmo, amigavel, agitado, caseiro):");
		filters[4] = EntradaTeclado.leString();
		
		
		
		return filters;
	} 
	
	
	
	public static void printMenuPesquisa(ArrayList<Animal> lista, Guardian user) throws SQLException, IOException, StandardException{

		int op = -1;
		
		//imprimimos a lista
		for(int i = 0; i < lista.size(); i++){
			System.out.print(i + ":\n");
			lista.get(i).printAnimal();
		}
		
		System.out.println("Para adicionar as informacoes de algum animal digite 0;");
		System.out.println("Para sair digite 1.");
		op = EntradaTeclado.leInt();
		while(op != 1){
			if(op == 0){
				
				System.out.println("Digite o indice do animal que deseja adicionar na sua lista de interesses");
				user.addAnimalInteressante(lista.get(EntradaTeclado.leInt()));
			}
			
		}
		
		
		
	}
	
	
	/**
	 * Realiza a busca utilizando um vetor de filtros
	 * @param user - Dados do Guardian
	 * @param fields - numero de campos na tabela do objeto a ser recuperado no BD
	 * @throws IOException
	 * @throws SQLException
	 * @throws StandardException 
	 */
	public static void busca_filtros(Guardian user, int fields) throws IOException, SQLException, StandardException{
		String[] filter = gera_filtros();
		Statement s;
		int count = 0;
		String aux = "select * from Animals ";
		if(!filter[0].equals("*")){
			if(count == 0 ) aux += " where ";
			aux = aux + "tipo='" + filter[0] + "'";
			count++;
		}
		
		if(!filter[1].equals("*")){
			if(count == 0 ) aux += " where ";
			else aux+= " and ";
			count++;
			aux = aux + " sexo = '" + filter[1] + "'";
		}
		
		if(!filter[2].equals("*")){
			if(count == 0 ) aux += " where ";
			else aux+= " and ";
			count++;
			aux = aux + " porte = '" + filter[2] + "'";
		}
		
		if(!filter[3].equals("*")){
			if(count == 0 ) aux += " where ";
			else aux+= " and ";
			count++;
			aux = aux + " pelagem = '" + filter[3] + "'";
		}
		
		if(!filter[4].equals("*")){
			if(count == 0 ) aux += " where ";
			else aux+= " and ";
			count++;
			aux = aux + " temperamento = '" + filter[4] + "'";
		}
		
		System.out.println(aux);
		
		ArrayList<Animal> l = new ArrayList<Animal>();
		
		try {
			
			s = ConnectionDb.con.createStatement();
			ResultSet result = s.executeQuery(aux);
			//String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
			while (result.next()){
            	String[] v = new String[fields + 1];
            	
            	for(int i = 1; i <= fields; i++){
            		v[i] = result.getString(i);
//            		System.out.println("Buscado : "+ i +"  " + v[i]);
            		
            	}
            	Animal a = new Animal(v); //copia info do animal
        		l.add(a); //adiciona o animal
            	
            	
            	
			}
			result.close();
			s.close();
		} catch (SQLException e) {
			System.out.println("Erro ao lista tabela");
			e.printStackTrace();
		}
		
		//impressao da lista obtida com a ultima filtragem
				for(int i = 0; i < l.size(); i++){
					System.out.print(i + ":\n");
					l.get(i).printAnimal();
					
				}
		
		
		
				
	}
	
	
	/**
	 * Imprime a lista de animais que interessam ao Guardian e oferece as operacoes sobre ela
	 * @param user - dados do Guardian
	 * @return int op - operacao para conduzir a continuacao ou nao do programa
	 * @throws IOException
	 * @throws SQLException
	 * @throws StandardException 
	 */
	public static int printMenuGuardianInteresses(Guardian user) throws IOException, SQLException, StandardException{
		int op = -1;
		
		user.printAnimaisInteressantes();
		System.out.println("Para verificar as informacoes de algum animal na sua lista de interesses digite 0;");
		System.out.println("Para remover algum animal da sua lista de interesses digite 1;");
		System.out.println("Para fazer uma pesquisa com o uso de filtros digite 2;\nPara sair digite 3.");
		op = EntradaTeclado.leInt();
		while(op != 3){
			switch(op){
				case 0:
					if(user.getInterest_list().size() > 0){
						System.out.println("Sua Lista:");
						user.printAnimaisInteressantes();
					
						System.out.println("Baseado na lista acima, digite o indice do animal que deseja acessar.");
						user.getInterest_list().get(EntradaTeclado.leInt()).printAnimal();
					}
					else{
						System.out.println("Sua lista esta vazia. Retorne ao Menu e pesquise alguns animais para adiciona-los!");
					}
					
					break;
					
				case 1:
					System.out.println("Sua Lista:");
					user.printAnimaisInteressantes();
					
					System.out.println("Baseado na lista acima, digite o indice do animal que deseja remover de sua lista.");
					user.getInterest_list().remove(EntradaTeclado.leInt());
					break;
				case 2:
					System.out.println("Digite, conforme requisitado, os campos que descrevem o animal questa procurando, caso nao tenha certeza digite '*' (sem aspas)." );
					busca_filtros(user, 13); //numero de campos na tabela Animals
					break;
					
					
			}
			
			System.out.println("Para verificar as informacoes de algum animal na sua lista de interesses digite 0;");
			System.out.println("Para remover algum animal da sua lista de interesses digite 1;");
			System.out.println("Para fazer uma pesquisa com o uso de filtros digite 2;\nPara sair digite 3.");
			op = EntradaTeclado.leInt();
			
		}
		
		return op;
		
	}
	
	/**
	 * Realiza a verificação de dados inseridos pelo usuario para efetuar ou não um login
	 * @param login - login do usuario
	 * @param passwd - senha do usuario
	 * @return User user - retorna um objeto User contendo as informacoes correspondentes aos dados inseridos
	 * @throws SQLException
	 */
	public static User verificationLogin(String login, String passwd) throws SQLException{
		String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
		//System.out.println(sql);
		User user;
		if(ConnectionDb.sqlExists(sql)){
			System.out.println("Usu�rio encontrado");
			user = ConnectionDb.getUserDB(login);
		}
		else return null;
		
		return user;
	}
	
	public static void main(String[] args) throws IOException, SQLException, StandardException{
		User user = null;
		int cad = -1;
		
		ConnectionDb.ConnectWithDatabase();
		
		System.out.println("Deseja fazer login? Pressione 0\nDeseja se cadastrar? Pressione 1");
		cad = EntradaTeclado.leInt();
		
		if(cad == 1){
			System.out.println("Para se cadastrar como uma ONG digite 1, senao digite 0.");
			cad = EntradaTeclado.leInt();
			if(cad == 1){
				Ong ong = new Ong();
				ong.putInDatabase();
			}
			
			else {
				Guardian g = new Guardian();
				g.putInDatabase();
			}
			
			
		}
		
		
		if (cad==0){
			//ConnectionDb.closeDB();
			//ConnectionDb.ConnectWithDatabase();
		
			if (user == null){
		
				System.out.println("Digite seus dados");
				System.out.print("Login: ");
		
				String login = EntradaTeclado.leString();
				System.out.print('\n');
				System.out.print("Senha: ");
				String senha = EntradaTeclado.leString();
				System.out.print('\n');
		
				//Autenticao dos dados retorna um objeto User
		
				//obtencao do tipo sem o login efetivo
				// t = EntradaTeclado.leInt();
				//User u = new User();
		
				user = verificationLogin(login,senha);
				if(user == null) System.out.println("Usu�rio ou senha incorreta");
			}
			
			int op = -1;
			
			while(user != null && op != 3){
				if(user.getType() == 1){ //se for ONG
					Ong ong = ConnectionDb.getOngDB(user);
					op = printMenuOng(ong);
			
				}		
		
				else{ //se for pessoa fisica
					Guardian g = ConnectionDb.getGuardianDB(user);
					//System.out.println("Email : "+ g.getLogin());
					op = printMenuGuardianMain(g);
			
				}
			}
		
			//ConnectionDb.closeDB();
		}
		
		if (cad ==3){
			System.out.println("Users ------ >");
			ConnectionDb.getAllTable("Users", 3);
			System.out.println("Guardians ------ >");
			ConnectionDb.getAllTable("Guardians", 3);
			System.out.println("Ongs  ------ >");
			ConnectionDb.getAllTable("Ongs", 6);
			//User u = ConnectionDb.getUserDB("ongteste");
			//Ong g = ConnectionDb.getOngDB(u);
			
			//String s = "insert into Ongs(email,adress,phone,cnpj,description,login) values('Ong@email2','Rua da ong2','32419262','123123123','Uma ong legal',(select login from Users where login='ongteste'))";
			//String s1 = "select * from Ongs";
			//ConnectionDb.insertTable(s, s1);
		
		}
		
		
		ConnectionDb.closeDB();
	}

}