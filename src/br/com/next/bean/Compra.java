package br.com.next.bean;

import java.util.Date;

public class Compra {
	private Date compra;
	private double valor;
	private Date data;
	
	// Método
	public Compra(Date data, double valor) {
		this.data = data;
		this.valor = valor;
	}
	
	// Getters e Setters
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getCompra() {
		return compra;
	}
	public void setCompra(Date compra) {
		this.compra = compra;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
