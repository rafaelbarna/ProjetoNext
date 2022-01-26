package br.com.next.utils;

import java.io.InputStream;
import java.util.Scanner;

import br.com.next.bo.ContaBO;

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
				+ "4 - Aleatório\n");
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
	
	public void menuSeguro() {
		System.out.println("Seguros:"
				+ "1 - Sobre Seguros - 1"
				+ "2 - Seguro Morte - 2"
				+ "3 - Seguro Invalidez - 3"
				+ "4 - Seguro Desempregro - 4");
	}
	
	public void sobreSeguro() {
		System.out.println("Sobre Seguros:"
				+ "Seguro Morte"
				+ "I. Indenização por despesas médico-hospitalares, ou por perda parcial ou\n"
				+ "total do funcionamento dos membros ou órgãos;\n"
				+ "II. Reembolso de custos em diagnóstico de doenças graves, como infarto,\n"
				+ "acidente vascular cerebral e câncer;\n"
				+ "III. Assistência funeral, para você e a sua família.\n"
				+ "IV. O valor do seguro individual é de R$36,00 ao ano."
				+ "\n"
				+ "Seguro Invalidez:\r\n"
				+ "I. Invalidez parcial: ocorre quando há uma perda parcial das funções. Por\r\n"
				+ "exemplo, uma pessoa que sofre um acidente e perda a visão em um só\r\n"
				+ "dos olhos.\r\n"
				+ "II. Invalidez total: ocorre quando há uma perda total das funções.\r\n"
				+ "Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um\r\n"
				+ "acidente e perde a visão em ambos os olhos.\r\n"
				+ "III. O valor do seguro individual é de R$26,00 ao ano."
				+ "\n"
				+ "Seguro Desemprego"
				+ "I. Necessário trabalhar com registro CLT, com o tempo mínimo de\r\n"
				+ "estabilidade de 12 meses.\r\n"
				+ "II. Válido apenas para desligamento involuntários e sem justa causa.\r\n"
				+ "III. Não é valido em caso de demissão em massa (10% ou mais de\r\n"
				+ "demissões simultâneas) ou falência/encerramento das atividades.\r\n"
				+ "IV. O valor do seguro individual é de R$16,00 ao ano.\r\n");
		}
	}
