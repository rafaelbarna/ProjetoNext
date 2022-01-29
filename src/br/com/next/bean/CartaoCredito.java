package br.com.next.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CartaoCredito extends Cartao {
	
	private double limite;
	private List<Compra> compra;
	private Date dataVencimento;
	private double valorFatura;
	private Apolice apolice;
	
	public CartaoCredito(String numero, Bandeira bandeira, String senha, boolean ativo, double limite, Date dataVencimento) {
		super(numero, bandeira, senha, ativo);
		this.limite = limite;
		this.compra = new ArrayList<Compra>();
		this.dataVencimento = this.getDateAdd1Month();
		this.valorFatura = 0;
	}
	
	public Date getDateAdd1Month() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date data = cal.getTime();
		return data;
	}
	
	public Apolice getApolice() {
		return apolice;
	}
	public void setApolice(int contrataMesSeguro, TipoSeguro tipoSeguro) {
		this.apolice = new Apolice();
	}
	public double getLimite() {
		return limite;
	}
	public List<Compra> getCompras() {
		return compra;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public double getValorFatura() {
		return valorFatura;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	public void setCompras(List<Compra> compras) {
		this.compra = compra;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}
}
