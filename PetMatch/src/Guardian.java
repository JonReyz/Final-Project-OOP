import java.io.IOException;
import java.util.ArrayList;

public class Guardian extends User {
	private String name; //nome do usuario
	private String email; //email - link do faceboook?
	private ArrayList<Animal> interest_list; //lista de interesses pra adocao do usuario 
	
	public Guardian() throws IOException{
		
		System.out.println("Digite o seu nome");
		name = EntradaTeclado.leString();
		
		System.out.println("Digite o seu email para contato");
		email= EntradaTeclado.leString();
		
		interest_list = new ArrayList<Animal>();
		
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
	
	
}

