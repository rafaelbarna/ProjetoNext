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
	private List<CartaoDebito> cartaoDebito = new ArrayList<>();
	private List<CartaoCredito> cartaoCredito = new ArrayList<>();
	
	
	// Cria lista de CD caso vazio. Se não adiciona CD a lista com dados do Main (7)
	public void addCartaoCD(CartaoDebito cartaoDebito) {
		if (this.cartaoDebito == null) {
			return;
		}
		this.cartaoDebito.add(cartaoDebito);
	}

	// Cartãos?
	public List<CartaoDebito> getCartaos() {
		return cartaoDebito;
	}

	public void setCartoes(List<CartaoDebito> cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}
	
	// Cria lista de CC caso vazio. Se não adiciona CC a lista com dados do Main (7)
	public void addCartaoCC(CartaoCredito cartaoCredito) {
		if (this.cartaoCredito == null) {
			this.cartaoCredito = new ArrayList<CartaoCredito>();
		}
		this.cartaoCredito.add(cartaoCredito);
	}
	
	
	
	
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

	public List<CartaoCredito> getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	
}
