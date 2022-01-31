package br.com.next.bean;

import java.util.Date;
import java.util.List;

public class Apolice2 {

	public String id;
	public double valor;
	public String descricao;
	private Date assinatura;
	private Date carencia;
	private List<Seguro> seguro;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Date assinatura) {
		this.assinatura = assinatura;
	}

	public Date getCarencia() {
		return carencia;
	}

	public void setCarencia(Date carencia) {
		this.carencia = carencia;
	}

	public List<Seguro> getSeguro() {
		return seguro;
	}

	public void setSeguro(List<Seguro> seguro) {
		this.seguro = seguro;
	}

}
