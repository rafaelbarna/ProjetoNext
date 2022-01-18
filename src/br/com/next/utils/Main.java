package br.com.next.utils;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.Endereco;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoChavePix;
import br.com.next.bean.TipoConta;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu();

		int opcaoEscolhida = -1;

		while (opcaoEscolhida != 6) {

			menu.exibirMenu();
			opcaoEscolhida = scanner.nextInt();

			if (opcaoEscolhida == 1) {
				Cliente cliente = Main.cadastrarCliente(scanner);
				if (cliente == null) {
					continue;
				}

				menu.exibirMenuConta();
				int opcaoConta = scanner.nextInt();

				String cpf = cliente.getCpf();

				List<Conta> lConta = BancoDeDados.buscarContaPorCliente(cpf);

				for (Conta conta : lConta) {
					if (opcaoConta == conta.getTipoConta().getId()) {
						System.out.println("Conta já criada");
						continue;
					}
				}

//				new ContaBO(cliente, (opcaoConta == 1 ? TipoConta.CORRENTE : TipoConta.POUPANCA));

				if (opcaoConta == 1) {
					new ContaBO(cliente, TipoConta.CORRENTE);
				} else {
					new ContaBO(cliente, TipoConta.POUPANCA);
				}
			}

			else if (opcaoEscolhida == 2) {
				System.out.print("Número da conta que recebe: ");
				String numeroContaRecebe = scanner.next();
				Conta contaRecebe = BancoDeDados.buscaContaPorNumero(numeroContaRecebe);

				if (contaRecebe == null) {
					continue;
				}

				System.out.print("Número da conta que paga: ");
				String numeroContaPaga = scanner.next();

				Conta contaPaga = BancoDeDados.buscaContaPorNumero(numeroContaPaga);

				if (contaPaga == null) {
					continue;
				}

				System.out.print("Valor da transferência: ");
				double valor = scanner.nextDouble();

				ContaBO contaBO = new ContaBO(contaPaga);
				contaBO.transferirDeContaParaConta(contaRecebe, valor);
			}

			else if (opcaoEscolhida == 3) {

				System.out.println("Número da conta: ");
				String numeroConta = scanner.next();

				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				System.out.println("Digite o valor: ");
				double valor = scanner.nextDouble();

				ContaBO contaBO = new ContaBO(conta);

				contaBO.depositar(valor);
			}

			else if (opcaoEscolhida == 4) {
				System.out.print("Número da conta: ");
				String numeroConta = scanner.next();

				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				ContaBO contaBO = new ContaBO(conta);
				contaBO.consultarSaldo();
			}

			else if (opcaoEscolhida == 5) {
				List<Conta> lConta = BancoDeDados.buscarTodasAsContas();

				for (Conta conta : lConta) {
					ContaBO contaBO = new ContaBO(conta);
					contaBO.debitoCredito();
				}
			}

			else if (opcaoEscolhida == 6) {
				System.out.print("Número da conta que recebe: ");
				String numeroConta = scanner.next();
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				menu.exibirMenuPix();

				int tipoPix = scanner.nextInt();
				String chavePix = "";
				TipoChavePix tcp = null;

				if (tipoPix == 4) {
					chavePix = UUID.randomUUID().toString();
					tcp = TipoChavePix.ALEATORIO;
					
				}
				else {
					System.out.println("Informe a chave: ");
					chavePix = scanner.next();
				}
				
				Pix pix = new Pix();
				pix.setAtivo(true);
				pix.setChave(chavePix);
				
				if (tipoPix == 1) {
					tcp = TipoChavePix.CPF;
				}
				else if (tipoPix == 2) {
					tcp = TipoChavePix.EMAIL;
				}
				else if (tipoPix == 3) {
					tcp = TipoChavePix.TELEFONE;
				}
				
				pix.setTipoChavePix(tcp);
				
				ContaBO contaBO = new ContaBO(conta);
				contaBO.adicionarPix(pix);
			}
			
			else if (opcaoEscolhida == 7) {
				System.out.println("Número da conta que paga: ");
				String numeroConta = scanner.next();
				
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);
				
				if (conta == null) {
					continue;
				}
				
				System.out.print("Informe a chave PIX: ");
				String chavePix = scanner.next();
				
				Conta contaRecebe = BancoDeDados.getContaPorPix(chavePix);
				
				if (contaRecebe == null) {
					System.out.print("Chave PIX não cadastrada");
					continue;
				}
				
				System.out.println("Valor: ");
				double valor = scanner.nextDouble();
				ContaBO contaBO = new ContaBO(conta);
				contaBO.transferirDeContaParaConta(contaRecebe, valor);
			}

			else if (opcaoEscolhida == 8) {
				System.out.println("Até logo!");
				System.exit(0);
			}
		}
	}

	public static Cliente cadastrarCliente(Scanner scanner) {
		ClienteBO clienteBO = new ClienteBO();
		Endereco endereco = new Endereco();

		scanner.nextLine();
		System.out.print("Logradouro: ");
		String logradouro = scanner.nextLine();

		System.out.println("Número: ");
		int numero = scanner.nextInt();

		System.out.println("CEP: ");
		String cep = scanner.next();

		System.out.println("Bairro: ");
		String bairro = scanner.next();

		System.out.println("Cidade: ");
		String cidade = scanner.next();

		System.out.println("Estado: ");
		String estado = scanner.nextLine();

		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setCep(cep);

		scanner.nextLine();
		System.out.print("Digite o nome do cliente: ");
		String nome = scanner.nextLine();

		String cpf = " . ";

		while (true) {

			System.out.println("CPF: ");
			cpf = scanner.next();

			if (clienteBO.validarCpf(cpf)) {
				break;
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Data de Nascimento (dd/mm/aaaa): ");
		String data = scanner.next();

		try {
			Date dataNascimento = sdf.parse(data);
			return clienteBO.cadastrarCliente(nome, cpf, dataNascimento, endereco);

		} catch (ParseException e) {
			System.out.println("Cliente não cadastrado");
			return null;
		}

	}
}