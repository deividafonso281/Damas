public class Main {

	public static void main (String[] args) {
		
		//array com os caracteres indicando a posicao das pecas que serao movidas
		char[] pos = new char[10];

		//Le o arquivo "arq001.csv"
		CSVReader csv = new CSVReader();
		csv.setDataSource("arq001.csv");
		String commands[] = csv.requestCommands();
		

		//Cria o tabuleiro;
		Tabuleiro tabuleiro = new Tabuleiro();
		System.out.println("Tabuleiro inicial:");
		tabuleiro.Imprime();
		
		
		//Fazer os movimentos
		for (int i=0;i<commands.length;i++) {
			commands[i].getChars(0,5,pos,0);
			tabuleiro.mover(pos);
			System.out.printf("Source: %c%c\n", pos[0], pos[1]);
			System.out.printf("Target: %c%c\n", pos[3], pos[4]);
			tabuleiro.Imprime();
		}
	}
}
