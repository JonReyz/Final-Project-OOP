import java.io.IOException;


public class petMatchMain {

	public static void printMenuOng(Ong user) throws IOException{
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
	
	public static void printMenuGuardianMain(Guardian user) throws IOException{
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
				//printMenuGuardianSearch(user);
				break;
			case 2:
				printMenuGuardianCadastrados(user);
				break;
		}
	
	}
	
	public static void printMenuGuardianCadastrados(Guardian user) throws IOException{
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
	
	public static void printMenuGuardianInteresses(Guardian user){
		
	}
	
	public static void main(String[] args) throws IOException{
		
		
		System.out.println("Digite seus dados");
		System.out.print("Login: ");
		
		String login = EntradaTeclado.leString();
		System.out.print('\n');
		System.out.print("Senha: ");
		String senha = EntradaTeclado.leString();
		System.out.print('\n');
		
		//Autenticao dos dados retorna um objeto User
		
		
		
		User u = new User();
		
		if(u.getType() == 0){ //se for ONG
			
				printMenuOng((Ong)u);
			
		}
		
		else{ //se for pessoa fisica
			
				printMenuGuardianMain((Guardian)u);
			
		}
		
		
	}
	
}
