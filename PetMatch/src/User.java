import java.io.IOException;

public class User {
	private String login; //login 
	private String passwd; //senha
	private int type; // se � um guardi�o ou uma ong 
	
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

}



