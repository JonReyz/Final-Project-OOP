

import java.io.IOException;
import java.util.ArrayList;

public class Ong extends User{
	private String name;
	private String email;
	private String adress;
	private String phone;
	private String cnpj;
	private String description;
	private ArrayList<Animal> cadastrados;

	
	public Ong() throws IOException{
		
		super();
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
		
//		while(!verificaCnpj(aux)){
//			System.out.println("CNPJ invalido, por favor, digite novamente");
//			aux = EntradaTeclado.leString();
//		}
		cnpj = aux;
		
		System.out.println("Digite uma breve descricao da ONG");
		description = EntradaTeclado.leString();
		
		cadastrados = new ArrayList<Animal>();
		
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
		if(verificaCnpj(cnpj)) this.cnpj = cnpj;
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
	public boolean verificaCnpj(String cnpj){
		int[] vet = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int sum = 0;
		int r = 0;
		int verf1 = cnpj.charAt(12) - '0';
		int verf2 = cnpj.charAt(13) - '0';
		int aux = 0;
		
		
		
		//multiplicacao dos elementos do vetor pelo numero do cnpj
		for(int i = 0; i < vet.length; i++){
				vet[i] = vet[i] * (cnpj.charAt(i) - '0'); //transformando para inteiro
				
		}
		
		//soma dos elementos dos vetor
		for(int i = 0; i < vet.length; i++){
			sum = sum + vet[i];
		}
		
		r = sum % 11;
		
		if(r < 2) aux = 0;
		else{
			aux = 11 - r;
			
		}
		if(aux != verf1) return false;
		else{
			int[] vet2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
			
			for(int i = 0; i < vet2.length; i++){
				vet2[i] = vet2[i] * (cnpj.charAt(i) - '0');
				
			}
			
			for(int i = 0; i < vet2.length; i++){
				sum = sum + vet2[i];
			}
			r = sum % 11;
			
			if(r < 2) aux = 0;
			else aux = 11 - r;
			
			return (aux == verf2);
			

			
			
		}
		
		
		
		
		
	}	
	
	
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
	

}

//private String tipo; //gato ou cao ou...?
//private String nome; //nome do animal
//private String sexo; //femea ou macho 
//private boolean status; //adotado ou nao
//private String porte; //pequeno, medio ou grande
//private String pelagem; //curto, medio ou longo
//private String temperamento; //amigavel, timido
//private int idade; //idade do animal
//private boolean vacinacao; //completamento vacinado ou nao
//private User responsavel; //ong ou usuario responsavel pela adocao
//private String descricao; // uma descri��o b�sica do animal





