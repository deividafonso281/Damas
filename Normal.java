public class Normal extends Peca {
	
	Normal (char cor, int tipo) {
		super (cor, tipo);
	}
	
	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal) { 
		boolean s = super.podeMovimento (xInicial, xFinal, yInicial, yFinal);
		if (s) {
			if (cor == 'B') { 
				if (yInicial + 1 == yFinal && (xInicial + 1 == xFinal || xInicial - 1 == xFinal)) 
					return true;
				return false;
			}
			else if (cor == 'P'){ //se nao tem captura e e preta
				if (yInicial - 1 == yFinal && (xInicial + 1 == xFinal || xInicial - 1 == xFinal))
					return true;
				return false;
			}
		}
		return s;
	}
}
