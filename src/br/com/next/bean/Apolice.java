// SPRINT 4
// Cartao ~> Cartao Credito ~> Apólice

// Apólice é um DOC de Seguro, podendo ter mais de um Seguro na Apolice

package br.com.next.bean;

import java.util.Date;

public class Apolice {
	
	private int id;
	private int valor;
	private int valorTotal;
	private TipoSeguro tipoSeguro;
	private int contrataMesSeguro;
	private Date dataAssinatura;
	private Date dataCarencia;
	private Date dataValidade;
	private Seguro seguro;
	private CartaoCredito cartaoCredito;
	private Boolean escolhaRegra;

	// GETTERS AND SETTERS
	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	public int getId() {
		return id;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

	public Boolean getEscolhaRegra() {
		return escolhaRegra;
	}

	public void setEscolhaRegra(Boolean escolhaRegra) {
		this.escolhaRegra = escolhaRegra;
	}

	public int getContrataMesSeguro() {
		return contrataMesSeguro;
	}

	public void setContrataMesSeguro(int contrataMesSeguro) {
		this.contrataMesSeguro = contrataMesSeguro;
	}

	public Date getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Date getDataCarencia() {
		return dataCarencia;
	}

	public void setDataCarencia(Date dataCarencia) {
		this.dataCarencia = dataCarencia;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

}
