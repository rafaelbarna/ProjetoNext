package br.com.next.bean;

import java.util.Date;

public class Conta {

	private String numero;
	private double saldo;
	private Cliente cliente;
	private Pix pix;
	private TipoConta tipoConta;
	private Date data;
	
	
	
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
	
	
}
