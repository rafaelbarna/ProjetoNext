// SPRINT 4
// Cartao ~> Cartao Credito ~> Apólice ~> Seguro

package br.com.next.bean;

public class Seguro {
	
	private Integer id;
	private TipoSeguro tipoSeguro;
	private boolean regras;
	
	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	public boolean getRegras() {
		return regras;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public void setRegras(boolean regras) {
		this.regras = regras;
	}
}
