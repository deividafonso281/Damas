public class Tabuleiro {
	
	protected Peca [][] tabuleiro; //matriz que representa o tabuleiro
	protected int branca; //numero de pecas brancas no jogo
	protected int preta; //numero de pecas pretas no jogo
	protected boolean temJogo; //mostra se ainda tem jogo 
	protected int xF, yF; //mostra as coordenadas finais da ultima jogada
	protected char vez; //mostra de quem e a vez
	
	public Tabuleiro () { //construtor de tabuleiro
		
		tabuleiro = new Peca [8][8];
		branca = 12;
		preta = 12;
		temJogo = true;
		xF = yF = -1;
		vez = 'B';
		
		//percorre a matriz
		for (int j = 7; j >= 0; j--) {
			for (int i = 7; i >= 0 ; i--) {	
				if (j < 3) { //posiciona os brancos
					if ((i+j)%2 == 0) { 
						tabuleiro[i][j] = new Normal ('B', 0);
					}
					else { //estao inicialmente vazias
						tabuleiro[i][j] = new Normal ('-',-1);
					}
				}
				
				else if (j < 5) { //linhas 4 e 5 sao todos espacos vazios
					tabuleiro[i][j] = new Normal ('-', -1);
				}
				
				else { //posiciona os pretos linhas 6,7,8
					if ((i+j) % 2 == 0) {
						tabuleiro[i][j] = new Normal ('P', 0);
					}
					else { //esta vazio
						tabuleiro [i][j] = new Normal ('-', -1);
					}
				}
			}
		}
	}
	
	public void Imprime() {
		for (int j = 7; j >= 0; j--) {
			System.out.print ((j+1) + "\t");
			for (int i = 0; i <= 7; i++) {
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
		else { 
			branca--;
			if (branca == 0)
				temJogo = false;
		}
		tabuleiro[x][y].cor='-';
		tabuleiro [x][y].tipo=-1;
	}	
	
	public void moveNormal(int xInicial, int xFinal, int yInicial, int yFinal) {
		
		if (xF == xInicial && yF == yInicial) {
			;
		}
		else {
			if (xF == -1 && yF == -1)
				;
			else if (vez == 'B')
				vez = 'P';
			else 
				vez = 'B';
		}
		
		if (vez == tabuleiro[xInicial][yInicial].cor) { //ve se o jogador certo esta jogando
			
	        if (tabuleiro[xInicial][yInicial].tipo == 0 && tabuleiro[xFinal][yFinal].tipo == -1) {
				Normal n = new Normal (tabuleiro[xInicial][yInicial].cor, 0);
				boolean p = tabuleiro[xInicial][yInicial].podeMovimento(xInicial, xFinal, yInicial, yFinal);
			
				if (p) { //se for movimento simples
					n.tranformaDama(xFinal);
					if (n.tipo == 1)
						tabuleiro[xFinal][yFinal] = new Dama(tabuleiro[xInicial][yInicial].cor, 1);
					else 
						tabuleiro[xFinal][yFinal].tipo = 0;
					tabuleiro[xFinal][yFinal].cor = tabuleiro[xInicial][yInicial].cor;
					tabuleiro[xInicial][yInicial] = new Peca ('-', -1);
				}
				
				int deltaX = xFinal - xInicial;
				int deltaY = yFinal - yInicial;
				if (deltaX == 2 || deltaX == -2) { //possivel captura
					if (deltaY == 2 || deltaY == -2) {
						if (tabuleiro[xInicial][yInicial].cor == 'B') {
							if (tabuleiro[(xInicial+deltaX/2)][(yInicial+deltaY/2)].cor == 'P') { //se a peca no meio for da cor oposta
								remove (xInicial+deltaX/2, yInicial+deltaY/2);
								tabuleiro[xFinal][yFinal] = new Normal ('B', 0);
								tabuleiro[xInicial][yInicial] = new Normal ('-', -1);
								if (xFinal == 7) { //ransformou dama
									tabuleiro[xFinal][yFinal].tipo = 1;
								}
							}
						}
						else {
							if (tabuleiro[(xInicial+deltaX/2)][(yInicial+deltaY/2)].cor == 'B') { //se a peca no meio for da cor oposta
								remove (xInicial+deltaX/2, yInicial+deltaY/2);
								tabuleiro[xFinal][yFinal] = new Normal ('P', 0);
								tabuleiro[xInicial][yInicial] = new Normal ('-', -1);
								if (xFinal == 0) { //ransformou dama
									tabuleiro[xFinal][yFinal].tipo = 1;
								}
							}
						}
					}
				}
	        }
		}
		xF = xFinal;
		yF = yFinal;
	}

	public void moveDama (int xInicial, int xFinal, int yInicial, int yFinal) {
		if (xF == xInicial && yF == yInicial) {
			;
		}
		else {
			if (xF == -1 && yF == -1)
				;
			else if (vez == 'B')
				vez = 'P';
			else 
				vez = 'B';
		}
		if (vez == tabuleiro[xInicial][yInicial].cor) { //ve se o jogador certo esta jogando
			if (tabuleiro[xInicial][yInicial].podeMovimento(xInicial,xFinal,yInicial,yFinal)) {
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
				for (int i=1;i<Math.abs(deltax);i++) {
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
				for (int i=1;i<Math.abs(deltax);i++) {
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
		xF = xFinal;
		yF = yFinal;
	}

	void mover (char[] pos) {
		int yInicial = pos[0] - '1';
		int xInicial = pos[1] - 'a';
		int yFinal = pos[3] - '1';
		int xFinal = pos[4] - 'a';
		if (tabuleiro[xInicial][yInicial].getTipo()==0) {
			moveNormal(xInicial,  xFinal,  yInicial, yFinal);
		}
		else if (tabuleiro[xInicial][yInicial].tipo==1) {
			moveDama ( xInicial,  xFinal,  yInicial, yFinal);
		}
	}
}
