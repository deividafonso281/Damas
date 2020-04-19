public class Tabuleiro {
	
	protected Peca [][] tabuleiro;
	protected int branca;
	protected int preta;
	protected boolean temJogo;
	
	public Tabuleiro () { //construtor de tabuleiro
		
		tabuleiro = new Peca [8][8];
		branca = 12;
		preta = 12;
		temJogo = true;
		
		//percorre a matriz
		for (int i = 8; i > 0; i--) {
			for (int j = 8; j > 0; j--) {
				
				if (i < 4) { //posiciona os brancos
					if (i % 2 == 0) { //primeira 0 e terceira 2 linhas do tabuleiro
						if (j % 2 == 0) { //tem peca
							tabuleiro[i-1][j-1] = new Peca ('B', 0);
						}
						else { //estao inicialmente vazias
							tabuleiro[i-1][j-1] = new Peca ('-', -1);
						}
					}
					else { //segunda linha 1
						if (j % 2 == 1) { //tem peca
							tabuleiro[i-1][j-1] = new Peca ('B', 0);
						}
						else { //esta vazia
							tabuleiro[i-1][j-1] = new Peca ('-', 1);
						}
					}
				}
				
				else if (i < 6) { //linhas 4 e 5 sao todos espacos vazios
					tabuleiro[i-1][j-1] = new Peca ('-', -1);
				}
				
				else { //posiciona os pretos linhas 6,7,8
					if (i % 2 == 0) { //setima linha do tabuleiro 6
						if (j % 2 == 0) { //tem peca
							tabuleiro[i-1][j-1] = new Peca ('P', 0);
						}
						else { //esta vazio
							tabuleiro [i-1][j-1] = new Peca ('-', -1);
						}
					}
					else { 
						if (j % 2 == 1) {
							tabuleiro[i-1][j-1] = new Peca ('P', 0);
						}
						else { //esta vazio
							tabuleiro[i-1][j-1] = new Peca ('-', -1);
						}
					}
				}
			}
		}
	}
	
	public void Imprime () { //rever tanto esse metodo imprime como o construtor ta bem confuso
		for (int i = 8; i > 0; i--) {
			System.out.print (i + "\t");
			for (int j = 1; j < 9; j++) {
				System.out.print (tabuleiro[i-1][j-1].cor + "\t");
			}
			System.out.println ("");
		}
		
		System.out.println ("\ta\tb\tc\td\te\tf\tg\th");
	}
	
	public Tabuleiro remove (int x, int y, Tabuleiro tab) {
		if (tab.tabuleiro[x][y].cor == 'P') {
			tab.preta--;
			if (tab.preta == 0)
				tab.temJogo = false;
		}
		else { //em outra funcao tem que testar antes pra ver se tem uma peca no lugar indicado que vai ser removida
			tab.branca--;
			if (tab.branca == 0)
				tab.temJogo = false;
		}
		tab.tabuleiro[x][y].cor = '-';
		tab.tabuleiro [x][y].tipo = -1;
		return tab;
	}
	
	
	
	public Tabuleiro realizaMovimento (int xInicial, int xFinal, int yInicial, int yFinal, Tabuleiro tab) { //ta dando problema qdo ta fora do tabuleiro
		boolean p = tab.tabuleiro[0][0].podeMovimento(xInicial, xFinal, yInicial, yFinal);
        if (!p)
            return tab;
		if (tab.tabuleiro[xInicial][yInicial].tipo == -1)
			return tab;
		else if (tab.tabuleiro[xFinal][yFinal].tipo != -1)
			return tab;
		else if (tab.tabuleiro[xInicial][yInicial].tipo == 0) {
			Normal n = new Normal (tab.tabuleiro[xInicial][yInicial].cor, 0);
			p = n.podeMovimento(xInicial, xFinal, yInicial, yFinal, tab.tabuleiro[xInicial][yInicial]);
			if (p) {
				n.tranformaDama(n, xFinal);
				if (n.tipo == 1)
					tab.tabuleiro[xFinal][yFinal].tipo = 1;
				else 
					tab.tabuleiro [xFinal][yFinal].tipo = 0;
				tab.tabuleiro[xFinal][yFinal].cor = tab.tabuleiro[xInicial][yInicial].cor;
				tab.tabuleiro[xInicial][yInicial].tipo = -1;
				tab.tabuleiro[xInicial][yInicial].cor = '-';
				return tab;
			}
		}
		else {
			Dama d = new Dama (tab.tabuleiro[xInicial][yInicial].cor, 1); //precisa ver se todos os espacos
			p = d.podeMovimento(xInicial, xFinal, yInicial, yFinal); //q a dama percorre estao vazios
			if (p) {
				tab.tabuleiro[xFinal][yFinal].cor = tab.tabuleiro [xInicial][yInicial].cor;
				tab.tabuleiro[xFinal][yFinal].tipo = 1;
				tab.tabuleiro[xInicial][yInicial].cor = '-';
				tab.tabuleiro[xInicial][yInicial].tipo = -1;
				return tab;
			}
		}
		return tab;
	}	
}
