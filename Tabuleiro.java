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
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0 ; j--) {
				
				if (i < 3) { //posiciona os brancos
					if ((i+j)%2 == 0) { 
						tabuleiro[i][j] = new Peca ('B', 0);
					}
					else { //estao inicialmente vazias
						tabuleiro[i][j] = new Peca ('-',-1);
					}
				}
				
				else if (i < 5) { //linhas 4 e 5 sao todos espacos vazios
					tabuleiro[i][j] = new Peca ('-', -1);
				}
				
				else { //posiciona os pretos linhas 6,7,8
					if ((i+j) % 2 == 0) {
						tabuleiro[i][j] = new Peca ('P', 0);
					}
					else { //esta vazio
						tabuleiro [i][j] = new Peca ('-', -1);
					}
				}
			}
		}
	}
	
	public void Imprime () { //rever tanto esse metodo imprime como o construtor ta bem confuso
		for (int i = 7; i >= 0; i--) {
			System.out.print ((i+1) + "\t");
			for (int j = 0; j <= 7; j++) {
				System.out.print (tabuleiro[i][j].cor + "\t");
			}
			System.out.println ("");
		}
		
		System.out.println ("\ta\tb\tc\td\te\tf\tg\th");
	}
	
	public void remove (int x, int y) {
		if (tabuleiro[x][y].cor == 'P') {
			preta--;
			if (preta == 0)
				temJogo = false;
		}
		else { //em outra funcao tem que testar antes pra ver se tem uma peca no lugar indicado que vai ser removida
			branca--;
			if (branca == 0)
				temJogo = false;
		}
		tabuleiro[x][y].cor = '-';
		tabuleiro [x][y].tipo = -1;
	}
	
	
	
	public void realizaMovimento (int xInicial, int xFinal, int yInicial, int yFinal) { //ta dando problema qdo ta fora do tabuleiro
		boolean p = tabuleiro[0][0].podeMovimento(xInicial, xFinal, yInicial, yFinal);
        if (p&&tabuleiro[xInicial][yInicial].tipo != -1&&tabuleiro[xFinal][yFinal].tipo == -1) {
		if (tabuleiro[xInicial][yInicial].tipo == 0) {
			Normal n = new Normal (tabuleiro[xInicial][yInicial].cor, 0);
			p = n.podeMovimento(xInicial, xFinal, yInicial, yFinal, tabuleiro[xInicial][yInicial]);
			if (p) {
				n.tranformaDama(n, xFinal);
				if (n.tipo == 1)
					tabuleiro[xFinal][yFinal].tipo = 1;
				else 
					tabuleiro [xFinal][yFinal].tipo = 0;
				tabuleiro[xFinal][yFinal].cor = tabuleiro[xInicial][yInicial].cor;
				tabuleiro[xInicial][yInicial].tipo = -1;
				tabuleiro[xInicial][yInicial].cor = '-';
			}
		}
		else {
			Dama d = new Dama (tabuleiro[xInicial][yInicial].cor, 1); //precisa ver se todos os espacos
			p = d.podeMovimento(xInicial, xFinal, yInicial, yFinal); //q a dama percorre estao vazios
			if (p) {
				tabuleiro[xFinal][yFinal].cor = tabuleiro [xInicial][yInicial].cor;
				tabuleiro[xFinal][yFinal].tipo = 1;
				tabuleiro[xInicial][yInicial].cor = '-';
				tabuleiro[xInicial][yInicial].tipo = -1;
			}
		}
	}
	}

	public void moveDama (int xInicial, int xFinal, int yInicial, int yFinal) {
		if (tabuleiro[xInicial][xFinal].podeMovimento(xInicial,xFinal,yInicial,yFinal)) {
                int deltax = xFinal - xInicial;
                int deltay = yFinal - yInicial;
                int razaox;
                int razaoy;
		int xcaptura = xInicial;
		int ycaptura = yInicial;
                int pretas=0;
                int brancas=0;
                if (deltax>0) {
                        razaox = 1;
                }
                else {
                        razaox = -1;
                }
                if (deltay>0) {
                        razaoy = 1;
                }
                else {
                        razaoy = -1;
                }
                if (tabuleiro[xInicial][yInicial].cor == 'B') {
			for (int i=0;i<Math.abs(deltax);i++) {
                        	if (tabuleiro[xInicial+i*razaox][yInicial+i*razaoy].cor=='P') {
                        	        pretas++;
					xcaptura = xInicial+i*razaox;
					ycaptura = yInicial+i*razaoy;
                        	}
                        	else if (tabuleiro[xInicial+i*razaox][yInicial+i*razaoy].cor=='B') {
                                	brancas++;
                        	}
                	}
                        if (brancas==0) {
                                if (pretas<2) {
					if (pretas==1) {
						remove(xcaptura,ycaptura);
					}
					tabuleiro[xFinal][yFinal] = tabuleiro[xInicial][yInicial];
                                        tabuleiro[xInicial][yInicial] = new Normal('-',-1);
                                }
                        }
                }
                else if (tabuleiro[xInicial][yInicial].cor== 'P') {
			for (int i=0;i<Math.abs(deltax);i++) {
                        	if (tabuleiro[xInicial+i*razaox][yInicial+i*razaoy].cor=='P') {
                        	        pretas++;
                        	}
                        	else if (tabuleiro[xInicial+i*razaox][yInicial+i*razaoy].cor=='B') {
                                	brancas++;
					xcaptura = xInicial+i*razaox;
                                        ycaptura = yInicial+i*razaoy;
                        	}
                	}
                        if (pretas==0) {
                                if (brancas<2) {
					if (brancas == 1) {
						remove(xcaptura,ycaptura);
					}
					tabuleiro[xFinal][yFinal] = tabuleiro[xInicial][yInicial];
                                        tabuleiro[xInicial][yInicial] = new Normal('-',-1);
                                }
                        }
                }
		}
        }

	void mover (char[] pos) {
		int yInicial = pos[0] - '0';
		int xInicial = pos[1] - 'a';
		int yFinal = pos[3] - '0';
		int xFinal = pos[4] - 'a';
		if (tabuleiro[xInicial][xFinal].tipo==0) {
			realizaMovimento (xInicial,  xFinal,  yInicial, yFinal);
		}
		else if (tabuleiro[xInicial][xFinal].tipo==1) {
			moveDama ( xInicial,  xFinal,  yInicial, yFinal);
		}
	}
}
