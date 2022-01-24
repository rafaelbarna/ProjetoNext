package br.com.next.utils;

public class Menu {

	public void exibirMenu() {
		

		System.out.println("\n----MENU----\n");
		System.out.println("1 - Cadastrar Cliente - 1");
		System.out.println("2 - Transferência - 2");
		System.out.println("3 - Depósito - 3");
		System.out.println("4 - Saldo - 4");
		System.out.println("5 - Cadastrar PIX - 5");
		System.out.println("6 - Transferência PIX - 6");
		System.out.println("7 - Solicitar Cartões - 7");
		System.out.println("8 - Serviços para Cartões - 8");
		System.out.println("9 - Fazer compras - 9");
		System.out.println("0 - SAIR - 0");
	}
	
	public void exibirMenuConta() {
		System.out.println("\nTipo de Conta:\n"
				+ "1 - Conta Corrente\n"
				+ "2 - Conta Poupança\n");
	}

	public void exibirMenuPix() {
		System.out.println("\nTipo PIX:\n"
				+ "1 - CPF\n"
				+ "2 - Email\n"
				+ "3 - Telefone\n"
				+ "4 - Aleat�rio\n");
	}
	
	public void menuCartao() {
		System.out.println("\nMenu Cartão:\n"
				+ "1 - Status do Cartão"
				+ "2 - Bloquear Cartão");
	}
	
	public void menuCompra() {
		System.out.println("\nOque deseja comprar:\n"
				+ "1 - Mercado - 1\n"
				+ "2 - Roupas - 2\n"
				+ "3 - Eletrônicos - 3");
			}
		
	
}
