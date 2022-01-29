// SPRINT 4
// Cartao ~> Cartao Credito ~> Apólice ~> Seguro

package br.com.next.bean;

public class Seguro {
	
	private int id;
	private int contrataMesSeguro;
	private TipoSeguro tipoSeguro;
	private boolean regras;
	
	public Seguro(CartaoCredito cartao, int id, TipoSeguro ts, int contrataMesSeguro, boolean escolhaRegra) {
		super();
		this.setContrataMesSeguro(contrataMesSeguro);
		this.regras = escolhaRegra;
	}
	
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

	public int getContrataMesSeguro() {
		return contrataMesSeguro;
	}

	public void setContrataMesSeguro(int contrataMesSeguro) {
		this.contrataMesSeguro = contrataMesSeguro;
	}
}
