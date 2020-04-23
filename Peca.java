public class Peca {
	
	protected char cor; //P preto normal, B branco normal, C dama branca, K dama preta e - sem peca
	protected int tipo; //0 normal e 1 dama, -1 vazio

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
                if (yFinal < 0)
                        return false;
                // peca saiu do tabuleiro
                return true;
        }

	public int getTipo() {
		return tipo;
	}
	public char getCor () {
		return cor;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void setCor(char cor) {
		this.cor = cor;
	}
}
 
