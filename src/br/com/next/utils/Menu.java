package br.com.next.utils;

public class Menu {

	public void exibirMenu() {
		
		System.out.println("\n\n1 - Cadastrar Cliente - 1");
		System.out.println("2 - Transferencia - 2");
		System.out.println("3 - Depósito - 3");
		System.out.println("4 - Saldo - 4");
		System.out.println("5 - Crédito | Débito - 5");
		System.out.println("6 - Cadastrar PIX - 6");
		System.out.println("7 - Transferência PIX - 7");
		System.out.println("8 - SAIR - 8");
	}
	
	public void exibirMenuConta() {
		System.out.println("Tipo de Conta\n"
				+ "1 - Conta Corrente\n"
				+ "2 - Conta Poupança\n");
	}

	public void exibirMenuPix() {
		System.out.println("Tipo PIX:\n"
				+ "1 - CPF\n"
				+ "2 - Email\n"
				+ "3 - Telefone\n"
				+ "4 - Aleatório\n");
		
	}
}
