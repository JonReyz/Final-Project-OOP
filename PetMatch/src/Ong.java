
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.sql.ResultSet;

public class Ong extends User{
	private String name;
	private String email;
	private String adress;
	private String phone;
	private String cnpj;
	private String description;
	private ArrayList<Animal> cadastrados;
	//Vetor usado para a validacao do CNPJ
	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	
	public Ong() throws IOException, StandardException{
		super(1);
		String aux;
		
		System.out.println("Digite o nome da sua ONG.");
		name = EntradaTeclado.leString();
		
		System.out.println("Digite o email para contato com a ONG.");
		email = EntradaTeclado.leString();
		
		System.out.println("Digite o endereco da ONG");
		adress = EntradaTeclado.leString();
		
		System.out.println("Digite o numero de telefone para contato");
		phone = EntradaTeclado.leString();
		
		System.out.println("Digite o CNPJ da ONG");
		aux = EntradaTeclado.leString();
		
		while(!isValidCNPJ(aux)){
			System.out.println("CNPJ invalido, por favor, digite novamente");
			aux = EntradaTeclado.leString();
		}
		cnpj = aux;
		
		System.out.println("Digite uma breve descricao da ONG");
		description = EntradaTeclado.leString();
		
		cadastrados = new ArrayList<Animal>();
		getAnimalsOng();
		
	}
	
	/**
	 * Construtor alternativo para preencher um objeto Ong baseado nos dados obtidos de um User e do BD
	 * @param u - User contendo info para construir um objeto User
	 * @param email - email da ONg
	 * @param adress - endereco da ong
	 * @param phone - telefone da ong
	 * @param cnpj - cnpj da ong
	 * @param des - descricao da ong
	 */
	public Ong(User u, String email, String adress, String phone, String cnpj, String des) {
		super(u.getLogin(),u.getPasswd(),u.getType());
		this.adress = adress;
		this.email = email;
		this.cnpj = cnpj;
		this.description = des;
		this.phone = phone;
		this.cadastrados = new ArrayList<Animal>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if(isValidCNPJ(cnpj)) this.cnpj = cnpj;
		else System.out.println("CNPJ invalido. Acao cancelada.");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Animal> getCadastrados() {
		return cadastrados;
	}

	public void setCadastrados(ArrayList<Animal> cadastrados) {
		this.cadastrados = cadastrados;
	}

	/**
	 * Verifica a validacao de um cnpj sem caracteres especiais (apenas numeros)
	 * @param cnpj - string contendo os caracteres do cnpj
	 * @return true - se for valido
	 * 		   false - se for invalido
	 */
	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
	     
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
	        soma += digito*peso[peso.length-str.length()+indice];
	    }
	    soma = 11 - soma % 11;
	    return soma > 9 ? 0 : soma;
	}
		
	public static boolean isValidCNPJ(String cnpj) {
	   
		if ((cnpj==null)||(cnpj.length()!=14)) return false;
			
	    Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
		
		return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	}	
	
	
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
		//System.out.println("Finga que aqui tem um dog");
	}
	/**
	 * Imprime os dados de uma ONG
	 */
	public void printOng(){
		System.out.print("Nome da ONG: " + name + "\n");
		
		System.out.print("Email: " + email + "\n");
		
		System.out.print("Endereco: " + adress + "\n");
		
		System.out.print("Telefone: " + phone + "\n");
		
		System.out.print("CNPJ: " + cnpj + "\n" );
		
		System.out.print("Descricao da ONG:\n" + description + "\n");
		
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
	 * Funcao para adicionar novas informacoes à tabela Ongs no Banco de Dados.
	 */	
	public void putInDatabase(){
		super.putInDatabase();
		String sql = "insert into Ongs(email,adress,phone,cnpj,description,login) values('"+this.email+"','"+this.adress+"','"+this.phone+"','"+this.cnpj+"','"+this.description+"',(select login from Users where login='"+super.getLogin()+"'))";
		String ver = "select * from Ongs";
		System.out.println("Entrei no ong -->  " + sql);
		// a considerar
		//ConnectionDb.ConnectWithDatabase();
		//talvez fazer uma verifica��o melhor
		try {
			ConnectionDb.insertTable(sql, ver);
		} catch (SQLException e) {
			System.out.println("Ong já existe");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAnimalsOng() throws StandardException{
		Statement s;
		try {
			s = ConnectionDb.con.createStatement();
			ResultSet result = (ResultSet) s.executeQuery("select * from Animals where responsavel = '"+ this.getLogin() +"'");
			//String sql = "select * from Users where login='"+login + "' and passwd = '"+ passwd +" '";
			while (((java.sql.ResultSet) result).next()){
            	String[] v = new String[13];
            	
            	for(int i = 1; i <= 12; i++){
            		v[i] = ((java.sql.ResultSet) result).getString(i);	
//            		System.out.println("Buscado : "+ i +"  " + v[i]);
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

