package br.com.next.bean;

public enum TipoSeguro {

	MORTE(0), INVALIDEZ(1), DESEMPREGO(2);

	private int id;

	TipoSeguro(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}
