import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class petMatchMain {

	/**
	 * Imprime o menu de um usuario do tipo Ong
	 * @param user - dados da ONG
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void printMenuOng(Ong user) throws IOException, SQLException{
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
		

		
	}
	
	/**
	 * Imprime o menu principal para um usuario Guardian (pessoa fisica)
	 * @param user - Dados do Guardian
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void printMenuGuardianMain(Guardian user) throws IOException, SQLException{
		int op = -1;
		
		System.out.println("Para gerenciar sua lista de animais interessantes digite 0;");
		System.out.println("Para procurar animais utilizando filtros digite 1;");
		System.out.println("Para gerenciar os animais pelos qual eh responsavel digite 2;");
		op = EntradaTeclado.leInt();
		
		switch(op){
			case 0:
				printMenuGuardianInteresses(user);
				break;
			case 1:
				busca_filtros(user, 13);//13 - numero de campos da classe Animal
				break;
			case 2:
				printMenuGuardianCadastrados(user);
				break;
		}
	
	}
	
	/**
	 * Imprime o menu para gerenciamento das adocoes pelo qual o Guardian eh responsavel
	 * @param user - Dados do Guardian
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void printMenuGuardianCadastrados(Guardian user) throws IOException, SQLException{
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
				user.addAnimalCadastrado(animal);
				break;
				
			}
			
			
			
			
			System.out.println("Para atualizar as informacoes sobre um animal digite 0;");
			System.out.println("Para apagar as informacoes sobre um animal digite 1;");
			System.out.println("Para cadastrar as informacoes sobre um novo animal digite 2;");
			System.out.println("Para sair digite 3.");
			op  = EntradaTeclado.leInt();
		}
		

		
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
	
	
	
	public static void printMenuPesquisa(ArrayList<Animal> lista, Guardian user) throws SQLException, IOException{

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
	 */
	public static void busca_filtros(Guardian user, int fields) throws IOException, SQLException{
		String[] filter = gera_filtros();
		Statement s;
		
		String aux = "select * from Animals ";
		if(filter[0] != "*"){
			aux = aux + "where tipo='" + filter[0] + "' and ";
		}
		
		if(filter[1] != "*"){
			aux = aux + "sexo = '" + filter[1];
		}
		
		if(filter[2] != "*"){
			aux = aux + "' and porte = '" + filter[2];
		}
		
		if(filter[3] != "*"){
			aux = aux + "' and  pelagem = '" + filter[3];
		}
		
		if(filter[4] != "*"){
			aux = aux + "' and temperamento = '" + filter[4] + " '";
		}
		
		ArrayList<Animal> l = new ArrayList<Animal>();
		try {
			s = ConnectionDb.con.createStatement();
			ResultSet result = s.executeQuery("select * from Animals where tipo='"+filter[0] + "' and sexo = '" + filter[1] + "' and porte = '" + filter[2] + "' and  pelagem = '" + filter[3] + "' and temperamento = '" + filter[4] + " '");
			//String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
			while (result.next()){
            	String[] v = new String[fields + 1];
            	
            	for(int i = 1; i <= fields; i++){
            		v[i] = result.getString(i);
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
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void printMenuGuardianInteresses(Guardian user) throws IOException, SQLException{
		int op = -1;
		
		user.printAnimaisInteressantes();
		System.out.println("Para verificar as informacoes de algum animal na sua lista de interesses digite 0;");
		System.out.println("Para remover algum animal da sua lista de interesses digite 1;");
		System.out.println("Para fazer uma pesquisa com o uso de filtros digite 2;\nPara sair digite 3.");
		op = EntradaTeclado.leInt();
		while(op != 3){
			switch(op){
				case 0:
					System.out.println("Sua Lista:");
					user.printAnimaisInteressantes();
					
					System.out.println("Baseado na lista acima, digite o indice do animal que deseja acessar.");
					user.getInterest_list().get(EntradaTeclado.leInt()).printAnimal();
					
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
		
		
		
	}
	
	//precisamos colocar isso em uma outra classe
	public static User verificationLogin(String login, String passwd) throws SQLException{
		String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
		System.out.println(sql);
		User user;
		if(ConnectionDb.sqlExists(sql)){
			System.out.println("Usu�rio encontrado");
			user = ConnectionDb.getUserDB(login);
		}
		else return null;
		
		return user;
	}
	
	public static void main(String[] args) throws IOException, SQLException{
		User user = null;
		
		//ConnectionDb.closeDB();
		ConnectionDb.ConnectWithDatabase();
		ConnectionDb.getAllTable("Users", 3);
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
		if(user.getType() == 1){ //se for ONG
				Ong ong = ConnectionDb.getOngDB(user);
				printMenuOng(ong);
			
		}
		
		else{ //se for pessoa fisica
				Guardian g = ConnectionDb.getGuardianDB(user);
				System.out.println("Email : "+ g.getLogin());
				printMenuGuardianMain(g);
			
		}
		
		ConnectionDb.closeDB();
	}

}