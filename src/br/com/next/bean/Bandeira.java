package br.com.next.bean;

public enum Bandeira {
	
	VISA(0), MASTERCARD(1);
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
