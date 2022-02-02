package br.com.next.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.next.utils.BancoDeDados;

public class Conta {

	private String numero;
	private double saldo;
	private Cliente cliente;
	private Pix pix;
	private TipoConta tipoConta;
	private Date data;
	private List<Cartao> cartao;

	// Métodos
	public void addCartao(Cartao cartao) {
		if (this.cartao == null) {
			this.cartao = new ArrayList<Cartao>();
		}
		this.cartao.add(cartao);
	}

	public void alteraCartaoCredito(CartaoCredito cartaoCredito) {
		List<Cartao> lCartao = new ArrayList<Cartao>();
		for (Cartao cartao : this.cartao) {
			if (cartao.getClass().getSimpleName().toLowerCase().contains("credito")) {
				lCartao.add(cartaoCredito);
			} else {
				lCartao.add(cartao);
			}
		}
		this.cartao = lCartao;
	}

	// Getters e Setters
	public String getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pix getPix() {
		return pix;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setPix(Pix pix) {
		this.pix = pix;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Cartao> getCartao() {
		return cartao;
	}
}
