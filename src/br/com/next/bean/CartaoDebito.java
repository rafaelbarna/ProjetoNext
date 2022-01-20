package br.com.next.bean;

public class CartaoDebito extends Cartao {
	
	private double limiteTransacao;

	public CartaoDebito(String numero, Bandeira bandeira, String senha, boolean ativo, double limite) {
		super(numero, bandeira, senha, ativo);
		this.limiteTransacao = limite;
	}

	public double getLimiteDebito() {
		return limiteTransacao;
	}

	public void setLimiteDebito(double limiteDebito) {
		this.limiteTransacao = limiteDebito;
	}
	
}
