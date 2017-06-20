

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.sql.ResultSet;

public class Guardian extends User {
	private String name; //nome do usuario
	private String email; //email - link do faceboook?
	private ArrayList<Animal> interest_list; //lista de interesses pra adocao do usuario 
	private ArrayList<Animal> cadastrados;
	
	public Guardian() throws IOException{
		super(0);
		System.out.println("Digite o seu nome");
		name = EntradaTeclado.leString();
		
		System.out.println("Digite o seu email para contato");
		email= EntradaTeclado.leString();
		
		interest_list = new ArrayList<Animal>();
		cadastrados = new ArrayList<Animal>();
		
	}
	
	/**
	 * Construtor alternativo para preencher um objeto Guardian baseado nos dados obtidos de um User e do BD
	 * @param u - User contendo info para o super construtor
	 * @param n - nome do Guardian
	 * @param e - email do guardian
	 * @throws StandardException 
	 */
	public Guardian(User u, String n, String e) throws StandardException {
		super(u.getLogin(),u.getPasswd(),u.getType());
		name = n;
		email= e;
		interest_list = new ArrayList<Animal>();
		cadastrados = new ArrayList<Animal>();
		getAnimalsGuardian();
	}

	public ArrayList<Animal> getCadastrados() {
		return cadastrados;
	}

	public void setCadastrados(ArrayList<Animal> cadastrados) {
		this.cadastrados = cadastrados;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Animal> getInterest_list() {
		return interest_list;
	}

	public void setInterest_list(ArrayList<Animal> interest_list) {
		this.interest_list = interest_list;
	}
	
	
	
	/**
	 * Imprime dados de um usuario tipo Guardian
	 */
	public void printGuardian(){
		System.out.print("Nome: " + name + "\n");
		
		System.out.print("Email: " + email + "\n");
	}
	
	//----------metodos para a lista de interesses------------------------
	
	/**
	 * Imprime a lista de animais interessantes
	 * @throws SQLException 
	 * @throws StandardException 
	 */
	public void printAnimaisInteressantes() throws SQLException, StandardException{
		for(int i = 0; i < interest_list.size(); i++){
			System.out.print(i + ":\n");
			interest_list.get(i).printAnimal();
		}
	}
	
	/**
	 * Apaga os dados de um animal da lista de interesses
	 * @param id - indice do animal a ser removido da lista
	 */
	public void apagaAnimalInteressante(int id){
		interest_list.remove(id);
	}
	
	/**
	 * Adiciona um novo animal a lista de interesse
	 * @param a - dados do animal a ser adiocionado
	 */
	public void addAnimalInteressante(Animal a){
		interest_list.add(a);
	}
	
	
	
	
	//---------metodos para a lista de animais cadastrados----------------
	
	/**
	 * Imprime a lista de animais cadastrados pelo usuario
	 * @throws SQLException 
	 * @throws StandardException 
	 */
	public void printAnimaisCadastrados() throws SQLException, StandardException{
		for(int i =0 ; i < cadastrados.size(); i++){
			//imprimir os dados do animal
			System.out.print(i + ":\n");
			cadastrados.get(i).printAnimal();
		}
	}
	
	/**
	 * Apaga os dados de um animal da lista de cadastros
	 * @param id - indice do animal a ser removido da lista
	 */
	public void apagaAnimalCadastrado(int id){
		cadastrados.remove(id);
	}
	
	/**
	 * Adiciona informacoes de um novo animal na lista de animais cadastrados
	 * @param a - info do novo animal
	 */
	public void addAnimalCadastrado(Animal a){
		cadastrados.add(a);
	}
	
	/**
	 * atualiza informacoes de um animal cadastrado
	 * @param id - indice do animal a ser modificado
	 * @throws IOException
	 */
	public void atualizaAnimalCadastrado(int id) throws IOException{
		System.out.println("Digite o numero da informacao que deseja atualizar, ou -1 para sair");
		System.out.print("0 - tipo;\n1 - nome;\n2 - sexo;\n3 - status;\n4 - porte;\n5 - pelagem;\n6 - temperamento;\n7 - idade;\n8 - vacinacao;\n9 - responsavel;\n10 - descricao.\n");
		int op = EntradaTeclado.leInt();
		while(op != -1){
			System.out.println("Digite a informacao atualizada");
			switch(op){
				case 0:
					cadastrados.get(id).setTipo(EntradaTeclado.leString());
					break;
				case 1:
					cadastrados.get(id).setNome(EntradaTeclado.leString());
					break;
				case 2:
					cadastrados.get(id).setSexo(EntradaTeclado.leString());
					break;
				case 3:
					String aux = EntradaTeclado.leString();
					if(aux.equalsIgnoreCase("adotado")) cadastrados.get(id).setStatus(true);
					else cadastrados.get(id).setStatus(false);
					break;
				case 4:
					cadastrados.get(id).setPorte(EntradaTeclado.leString());
					break;
				case 5:
					cadastrados.get(id).setPelagem(EntradaTeclado.leString());
					break;
				case 6:
					cadastrados.get(id).setTemperamento(EntradaTeclado.leString());
					break;
				case 7:
					cadastrados.get(id).setIdade(EntradaTeclado.leInt());
					break;
				case 8:
					String aux2 = EntradaTeclado.leString();
					if(aux2.equalsIgnoreCase("vacinado"))cadastrados.get(id).setVacinacao(true);
					else cadastrados.get(id).setVacinacao(false);
					break;
				//nao existe case para mudar o responsavel, se for o caso, descadastramos o animal
				case 9:
					System.out.println("Essa escolha nao te permite designar um novo responsavel para o Animal.\n Ela apagara os dados do animal do sistema.");
					System.out.println("Se deseja continuar digite 9 novamente, caso contrario, digite outra opcao do menu.");
					op = EntradaTeclado.leInt();
					if(op == 9){
						cadastrados.remove(id); //remover tb do banco de dados
					}
					
					
					break;
				case 10:
					cadastrados.get(id).setDescricao(EntradaTeclado.leString());
					break;
					
			}
			System.out.println("Digite o numero da informacao que deseja atualizar, ou -1 para sair");
			System.out.print("0 - tipo;\n1 - nome;\n2 - sexo;\n3 - status;\n4 - porte;\n5 - pelagem;\n6 - temperamento;\n7 - idade;\n8 - vacinacao;\n9 - responsavel;\n10 - descricao.\n");
			op = EntradaTeclado.leInt();
			
			
		}
	
	}
	
	/**
	 * Funcao para adicionar novas informacoes à tabela Guardians no Banco de Dados.
	 */
	public void putInDatabase(){
		super.putInDatabase();
		String sql = "insert into Guardians(name,email,login) values('"+this.name+"','" + this.email +"',"+"(select login from Users where login='" + super.getLogin()+"'))";
		String ver = "select * from Guardians";
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
	
	public void getAnimalsGuardian() throws StandardException{
		Statement s;
		try {
			s = ConnectionDb.con.createStatement();
			
			java.sql.ResultSet result =  s.executeQuery("select * from Animals where responsavel = '"+ this.getLogin() +"'");
			//String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
			while (((java.sql.ResultSet) result).next()){
	        	String[] v = new String[13];
	        	
	        	for(int i = 1; i <= 12; i++){
	        		v[i] = ((java.sql.ResultSet) result).getString(i);	
//	        		System.out.println("Buscado : "+ i +"  " + v[i]);
	        	}
	        	Animal a = new Animal(v); //copia info do animal
	    		cadastrados.add(a); //adiciona o animal
			}
			result.close();
			s.close();
		} catch (SQLException e) {
			System.out.println("Erro ao lista tabela");
			e.printStackTrace();
		}
	}
		
	
	
	
}