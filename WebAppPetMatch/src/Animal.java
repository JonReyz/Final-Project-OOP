
import java.io.IOException;
import java.sql.SQLException;

public class Animal {
	//foto do cachorro
	private int id; //id do animal no DB
	private String tipo; //gato ou cao ou...?
	private String nome; //nome do animal
	private String sexo; //femea ou macho 
	private boolean status; //adotado ou nao
	private String porte; //pequeno, medio ou grande
	private String pelagem; //curto, medio ou longo
	private String temperamento; //amigavel, timido
	private int idade; //idade do animal
	private boolean vacinacao; //completamento vacinado ou nao
	private User responsavel; //ong ou usuario responsavel pela adocao
	private String descricao; // uma descri��o b�sica do animal
	
	/**
	 * Construtor do objeto tipo Animal dado o usuario responsavel por gerenciar a adocao deste animal
	 * @param u - dados do usuario responsavel pelo animal
	 * @throws IOException
	 */
	public Animal(User u) throws IOException{
		
		String aux;
		
		System.out.println("Digite as caracteristicas do animal que deseja cadastrar.");
		
		System.out.println("Tipo do animal (gato, cao)");
		tipo = EntradaTeclado.leString().toLowerCase();
		
		
		System.out.println("Nome do animal");
		nome = EntradaTeclado.leString();
		
		System.out.println("Sexo do animal");
		sexo = EntradaTeclado.leString().toLowerCase();
		
		status = false; //setar o status do animal para false (n adotado)
		
		System.out.println("Porte do animal (pequeno, medio ou grande)");
		porte = EntradaTeclado.leString().toLowerCase();
		
		System.out.println("Descreva a pelagem do animal (pelo curto, longo...)");
		pelagem = EntradaTeclado.leString().toLowerCase();
		
		System.out.println("Descreva o temperamento do animal (amigavel, timido, ativo...)");
		temperamento = EntradaTeclado.leString().toLowerCase();
		
		System.out.println("Qual a idade do animal?");
		idade = EntradaTeclado.leInt();
		
		System.out.println("O animal esta COMPLETAMENTE vacinado? (digite sim ou nao)");
		aux = EntradaTeclado.leString().toLowerCase();
		
		if(aux.equals("sim")) vacinacao = true;
		else vacinacao = false;
		
		responsavel = u;
		
		System.out.println("Digite uma breve descricao com informacoes adicionais sobre o animal que podem ser importantes");
		descricao = EntradaTeclado.leString();
		
		
	}
	
	/**
	 * Construtor alternativo  no qual ja se passa os dados a partir do BD
	 * @param v - vetor de strigs contendo as informacoes do animal
	 * @throws SQLException
	 */
	public Animal(String[] v) throws SQLException{
		this.id = Integer.parseInt(v[1]);
		System.out.println(v.toString());
		this.nome = v[2];//os parametros q sao usados para a pesquisa sao construidos em lower-case para padronizar a pequisa 
		
		this.tipo = v[3];
		
		this.sexo = v[4];
		
		//if(v[5].equals('0'))this.status = true;
		//else this.status = false;
		
		this.porte = v[6];
		
		this.pelagem = v[7];
		
		this.temperamento = v[8];
		
		//n guardamos o campo email
		
		//this.idade = Integer.parseInt(v[10]);
		//if(v[11].equals(0)) this.vacinacao = true;
		//else this.vacinacao = false;
		
		//User user = ConnectionDb.getUserDB(v[12]);
		//this.responsavel = user;
		
		
		this.descricao = v[13];
		
		
		
		
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getPorte() {
		return porte;
	}


	public void setPorte(String porte) {
		this.porte = porte;
	}


	public String getPelagem() {
		return pelagem;
	}


	public void setPelagem(String pelagem) {
		this.pelagem = pelagem;
	}


	public String getTemperamento() {
		return temperamento;
	}


	public void setTemperamento(String temperamento) {
		this.temperamento = temperamento;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public boolean isVacinacao() {
		return vacinacao;
	}


	public void setVacinacao(boolean vacinacao) {
		this.vacinacao = vacinacao;
	}


	public User getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(User responsavel) {
		this.responsavel = responsavel;
	}
	

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Imprime os dados de um animal
	 * @throws SQLException 
	 */
	public void printAnimal() throws SQLException{
		
		//como imprimir a foto?
		
		System.out.print("\tTipo: " + tipo + "\n");
		
		System.out.print("\tNome: " + nome+ "\n");
		
		System.out.print("\tSexo: " + sexo+ "\n");
		
		System.out.print("\tStatus: ");
		
		if(status) System.out.print("\tadotado" + "\n");
		else System.out.print("\tdisponivel para adocao" + "\n");
		
		System.out.print("\tPorte: " + porte + "\n");
		
		System.out.print("\tPelagem: " + pelagem + "\n");
		
		System.out.print("\tTemperamento: " + temperamento + "\n");
		
		System.out.print("\tIdade: " + idade + " anos\n");
		
		if(vacinacao) System.out.print("\tAnimal completamente vacinado\n");
		else System.out.print("\tAnimal nao esta completamente vacinado. Tratar com o Reponsavel\n");
		
		if (responsavel.getType() == 0){
			Ong ong = ConnectionDb.getOngDB(responsavel);
			ong.printOng();
		}
		else{ 
			Guardian guardian = ConnectionDb.getGuardianDB(responsavel);
			guardian.printGuardian(); //(?) aqui ta dando NullPointerException - creio q fazer uma funcao q popula a lista seja o suficiente
		}
		
		System.out.println("\tDescricao do Animal:");
		System.out.println("\t\t" + descricao);
		
	}
}