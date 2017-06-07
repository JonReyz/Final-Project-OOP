import java.io.IOException;
import java.util.ArrayList;

public class Ong extends User{
	private String email;
	private String adress;
	private String phone;
	private String cnpj;
	private String description;
	private ArrayList<Animal> cadastrados;

	
	public Ong() throws IOException{
		
		super();
		String aux;
		
		
		
		System.out.println("Digite o email para contato com a ONG.");
		email = EntradaTeclado.leString();
		
		System.out.println("Digite o endereco da ONG");
		adress = EntradaTeclado.leString();
		
		System.out.println("Digite o numero de telefone para contato");
		phone = EntradaTeclado.leString();
		
		System.out.println("Digite o CNPJ da ONG");
		aux = EntradaTeclado.leString();
		
		while(!verificaCnpj(aux)){
			System.out.println("CNPJ invalido, por favor, digite novamente");
			aux = EntradaTeclado.leString();
		}
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
	
	public void cadastrarNovoAnimal() throws IOException{
		Animal a = new Animal(this); //funcionaria atribuir um tipo Ong para uma variavel User?
		this.cadastrados.add(a);
		
	}
	
	
	

}




