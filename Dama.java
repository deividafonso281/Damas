public class Dama extends Peca{

	public Dama(char cor, int tipo) {
		super(cor,tipo);
	}

	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal) {	
		int deltax = xFinal - xInicial;
		int deltay = yFinal - yInicial;
		if (super.podeMovimento(xInicial,xFinal,yInicial,yFinal)&&Math.abs(deltax)==Math.abs(deltay)) {
			return true;
		}
		return false;
	}
}
