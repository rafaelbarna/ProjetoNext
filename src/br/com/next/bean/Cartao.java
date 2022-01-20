package br.com.next.bean;

import java.util.UUID;

public abstract class Cartao {
	
	private String id;
	private String numero;
	private String bandeira;
	private String senha;
	private boolean ativo;
	
	public Cartao(String numero, String bandeira, String senha, boolean ativo) {
		this.numero = numero;
		this.bandeira = bandeira;
		this.senha = senha;
		this.ativo = ativo;
		this.id = UUID.randomUUID().toString();
	}
	
	public String getNumero() {
		return numero;
	}
	public String getBandeira() {
		return bandeira;
	}
	public String getSenha() {
		return senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
