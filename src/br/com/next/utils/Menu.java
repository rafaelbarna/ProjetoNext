package br.com.next.utils;

public class Menu {

	public void exibirMenu() {
		
		System.out.println("1 - Cadastrar Cliente - 1");
		System.out.println("2 - Transferência - 2");
		System.out.println("3 - Depósito - 3");
		System.out.println("4 - Saldo - 4");
		System.out.println("5 - Taxas Crédito e Débito - 5");
		System.out.println("6 - Cadastrar PIX - 6");
		System.out.println("7 - Transferência PIX - 7");
		System.out.println("8 - Cartões - 8");
		System.out.println("sair - SAIR - sair");
	}
	
	public void exibirMenuConta() {
		System.out.println("Tipo de Conta\n"
				+ "1 - Conta Corrente\n"
				+ "2 - Conta Poupan�a\n");
	}

	public void exibirMenuPix() {
		System.out.println("Tipo PIX:\n"
				+ "1 - CPF\n"
				+ "2 - Email\n"
				+ "3 - Telefone\n"
				+ "4 - Aleat�rio\n");
	}
	
	public void menuCartao() {
		
	}
	
	public void menuCompra() {
		System.out.print("Oque deseja comprar?\n"
				+ "1 - Mercado - 1\n"
				+ "2 - Roupas - 2\n"
				+ "3 - Eletrônicos - 3");
			}
		
	
}
