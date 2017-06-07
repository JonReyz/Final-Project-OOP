import java.io.IOException;

public class User {
	private String login; //login 
	private String passwd; //senha
	private int type; // se e um guardiao ou uma ong 
	
	/**
	 * Cadastra novo usuario
	 * @throws IOException 
	 */
	public User() throws IOException{
		//lembrar de verificar banco de dados assegurando que n existem dados repetidos
		System.out.println("Digite seu login:");
		login = EntradaTeclado.leString();
		
		System.out.println("Digite sua senha:");
		passwd = EntradaTeclado.leString();
	
		System.out.println("Voce deseja criar um perfil de ONG ou de pessoa fisica?");
		System.out.println("0 - ONG;" + "\n" + "1 - Pessoa fisica");
		type = EntradaTeclado.leInt();
	
	}

	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	
	
	public String getPasswd(){
		return this.passwd;
	}
	
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	
	
	public int getType(){
		return this.type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	
}



