public class Normal extends Peca {
	
	Normal (char cor, int tipo) {
		super (cor, tipo);
	}
	
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
	
	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal, Peca p) { 
		boolean s = super.podeMovimento (xInicial, xFinal, yInicial, yFinal);
		if (s) {
			if (p.cor == 'B') { 
				if (xInicial + 1 == xFinal && (yInicial + 1 == yFinal || yInicial - 1 == yFinal)) 
					return true;
			}
			else if (p.cor == 'P'){ //se nao tem captura e e preta
				if (xInicial - 1 == xFinal && (yInicial + 1 == yFinal || yInicial - 1 == yFinal))
					return true;
			}
		}
		return s;
	}
}
