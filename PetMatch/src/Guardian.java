

import java.io.IOException;
import java.util.ArrayList;

public class Guardian extends User {
	private String name; //nome do usuario
	private String email; //email - link do faceboook?
	private ArrayList<Animal> interest_list; //lista de interesses pra adocao do usuario 
	private ArrayList<Animal> cadastrados;
	
	public Guardian() throws IOException{
		
		System.out.println("Digite o seu nome");
		name = EntradaTeclado.leString();
		
		System.out.println("Digite o seu email para contato");
		email= EntradaTeclado.leString();
		
		interest_list = new ArrayList<Animal>();
		cadastrados = new ArrayList<Animal>();
		
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
	 */
	public void printAnimaisInteressantes(){
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
	 */
	public void printAnimaisCadastrados(){
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
	
	
	
	
	
}
