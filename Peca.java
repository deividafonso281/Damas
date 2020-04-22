public class Peca {
	
	protected char cor; //P preto, B branco e - sem peca
	protected int tipo; //0 normal e 1 dama

	Peca (char cor, int tipo) {
		this.cor = cor;
		this.tipo = tipo;
	}
	
	public boolean podeMovimento (int xInicial, int xFinal, int yInicial, int yFinal) {
                //peca saiu do tabuleiro
                if (xFinal > 7 )
                        return false;
                if (yFinal > 7)
                        return false;
                if (xFinal < 0)
                        return false;
                if (yInicial < 7)
                        return false;
                // peca saiu do tabuleiro
                return true;
        }
}
 
