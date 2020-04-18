public class Peca {
	
	protected char cor; //P preto, B branco e - sem peca
	protected int tipo; //0 normal e 1 dama

	Peca (char cor, int tipo) {
		this.cor = cor;
		this.tipo = tipo;
	}
	
	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal) {
		if (xFinal > 8 || yFinal > 8)
			return false;
		if (xInicial > 8 || yInicial > 8)
			return false;
		return true;
	}
}

/*reqMovimento receber o movimento
 *  ver se e compativel com seu tipo normal/dama
 *  ver se e possivel no tabuleiro, se esta dentro das dimensoes  Peca
 *  ver se e um movimento normal ou uma captura
 *  tem que ser em uma diagonal 
 *  se for normal tem que andar so uma casa pra frente na diagonal
 *  se nao tem que achar como e a captura
 *  comunicar se com o tabuleiro pra que ele faca a remocao ou o movimento
 */ 
