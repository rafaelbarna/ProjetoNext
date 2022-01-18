package br.com.next.bean;

import java.util.Date;

public class Cliente {
	
	private String cpf;
	private String nome;
	private TipoCliente tipoCliente;
	private Date dataNascimento;
	private Endereco endereco;
	
	
	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
