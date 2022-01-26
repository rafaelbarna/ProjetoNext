// SPRINT 4
// Cartao ~> Cartao Credito ~> Apólice

// Apólice é um DOC de Seguro, podendo ter mais de um Seguro na Apolice

package br.com.next.bean;

import java.util.Calendar;

import br.com.next.utils.Utils;

public class Apolice {
	
	private int id;
	private TipoSeguro tipoSeguro;
	private Calendar dataAssinatura;
	private Calendar dataCarencia;
	private Calendar dataValidade;

	private Seguro seguro;
	
	public Apolice(int duracaoMes, TipoSeguro tipoSeguro) {
		this.id = Utils.numeroRandomico(1, 10);
		this.dataAssinatura = Calendar.getInstance();
		
		this.dataCarencia = getDataAssinatura();
		this.dataCarencia.add(Calendar.DAY_OF_YEAR, 15);
		
		this.dataValidade = getDataAssinatura();
		this.dataValidade.add(Calendar.MONTH, duracaoMes);
	}

	public int getId() {
		return id;
	}

	public Calendar getDataAssinatura() {
		return dataAssinatura;
	}

	public Calendar getDataCarencia() {
		return dataCarencia;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDataAssinatura(Calendar dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public void setDataCarencia(Calendar dataCarencia) {
		this.dataCarencia = dataCarencia;
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
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
}
