public class Peca {
	
	protected char cor; //P preto, B branco e - sem peca
	protected int tipo; //0 normal e 1 dama

	Peca (char cor, int tipo) {
		this.cor = cor;
		this.tipo = tipo;
	}
	
	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal) {
		if (xFinal > 7 || yFinal > 7)
			return false;
		if (xInicial > 7 || yInicial > 7)
			return false;
		return true;
	}
}
 
