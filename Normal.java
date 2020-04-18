public class Normal extends Peca {
	
	Normal (char cor, int tipo) {
		super (cor, tipo);
	}
	
	//movimento
	
	public Peca tranformaDama (Peca comum, int x) {
		if (comum.cor == 'P') {
			if (x == 0) //chegou do outro lado do tabuleiro
				comum.tipo = 1; //transforma em dama
		}
		else if (comum.cor == 'B') {
			if (x == 7) //chegou do outro lado do tabuleiro
				comum.tipo = 1; //transforma em dama
		}
		return comum;
	}
	
	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal, Tabuleiro t) { //nao sei como ver se pode ter o movimento com a captura e precisa realizar o movimento de fato no tabuleiro
		boolean s = super.podeMovimento (xInicial, xFinal, yInicial, yFinal);
		if (t.tabuleiro[xFinal][yFinal].tipo != -1)
			return false;
		if (t.tabuleiro[xInicial][yInicial].tipo == -1)
			return false;
		if (s) {
			if (t.tabuleiro[xInicial][yInicial].cor == 'B') { //se nao tem captura de pecas e e branca
				if (xInicial + 1 == xFinal && (yInicial + 1 == yFinal || yInicial - 1 == yFinal)) 
					return true;
			}
			else { //se nao tem captura e e preta
				if (xInicial - 1 == xFinal && (yInicial + 1 == yFinal || yInicial - 1 == yFinal))
					return true;
			}
		}
		return s;
	}
}
