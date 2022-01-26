package br.com.next.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.bean.Cartao;
import br.com.next.bean.CartaoCredito;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Conta;
import br.com.next.bean.Pix;

public class BancoDeDados {

	// Static não precisa de referãncia *
	private static Map<String, Conta> BANCO_DE_DADOS = new HashMap<String, Conta>();

	public static Conta buscaContaPorNumero(String numeroConta) {
		Conta conta = BancoDeDados.BANCO_DE_DADOS.get(numeroConta);

		if (conta == null) {
			System.out.println("Conta não encontrada");
			return null;
		}

		return conta;
	}

	public static void insereConta(String numeroConta, Conta conta) {
		BancoDeDados.BANCO_DE_DADOS.put(numeroConta, conta);
	}

	public static List<Conta> buscarContaPorCliente(String cpf) {
		List<Conta> lConta = new ArrayList<Conta>();

		for (Map.Entry<String, Conta> mapaConta : BancoDeDados.BANCO_DE_DADOS.entrySet()) {

			Conta conta = mapaConta.getValue();

			if (conta.getCliente().getCpf().equals(cpf)) {
				lConta.add(conta);
			}
		}
		return lConta;
	}

	public static List<Conta> buscarTodasAsContas() {
		List<Conta> lConta = new ArrayList<Conta>();

		for (Map.Entry<String, Conta> mapaConta : BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			Conta conta = mapaConta.getValue();
			lConta.add(conta);
		}
		return lConta;
	}

	public static Conta getContaPorPix(String chave) {
		for (Map.Entry<String, Conta> mapaConta : BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			Conta conta = mapaConta.getValue();

			Pix pix = conta.getPix();

			if (pix != null) {
				if (pix.getChave().equals(chave)) {
					return conta;
				}
			}
		}

		return null;
	}

	// Busca Cartão Débito no BD
	public static CartaoDebito buscaCartaoDebito(String numeroCartao) {
		for (Map.Entry<String, Conta> mapaConta : BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			Conta conta = mapaConta.getValue();
			
			List<CartaoDebito> listaCartao = conta.getlistaCartaoDebito();
			if (listaCartao != null) {
				for (CartaoDebito cartao : listaCartao) {
					if (cartao.getNumero().equals(numeroCartao)) {
						return cartao;
					}
				}
			}
		}
		System.out.println("Cartao não encontrado");
		return null;
	}
	
	// Busca Cartão Crédito no BD
	public static CartaoCredito buscaCartaoCredito(String numeroCartao) {
		for (Map.Entry<String, Conta> mapaConta : BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			Conta conta = mapaConta.getValue();
			
			List<CartaoCredito> listaCartao = conta.getlistaCartaoCredito();
			if (listaCartao != null) {
				for (CartaoCredito cartao : listaCartao) {
					if (cartao.getNumero().equals(numeroCartao)) {
						return cartao;
					}
				}
			}
		}
		System.out.println("Cartao não encontrado");
		return null;
	}
}