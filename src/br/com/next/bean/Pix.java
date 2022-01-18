package br.com.next.bean;

public class Pix {
	
	private TipoChavePix tipochavepix;
	private String chave;
	private boolean ativo;
	
	
	public TipoChavePix getTipochavepix() {
		return tipochavepix;
	}
	public String getChave() {
		return chave;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setTipoChavePix(TipoChavePix tipochavepix) {
		this.tipochavepix = tipochavepix;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
