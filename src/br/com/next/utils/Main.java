package br.com.next.utils;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import br.com.next.bean.Bandeira;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.Endereco;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoChavePix;
import br.com.next.bean.TipoCliente;
import br.com.next.bean.TipoConta;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;

public class Main {

	private static Bandeira bandeira;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu();

		int opcaoEscolhida = -1;

		while (opcaoEscolhida != 6) {

			menu.exibirMenu();
			opcaoEscolhida = scanner.nextInt();

			// Cadastrar novo cliente
			if (opcaoEscolhida == 1) {
				Cliente cliente = Main.cadastrarCliente(scanner);
				if (cliente == null) {
					continue;
				}

				menu.exibirMenuConta();
				int opcaoConta = scanner.nextInt();

				String cpf = cliente.getCpf();

				List<Conta> lConta = BancoDeDados.buscarContaPorCliente(cpf);

				// Se conta já cadastrada
				for (Conta conta : lConta) {
					if (opcaoConta == conta.getTipoConta().getId()) {
						System.out.println("Uma conta já está cadastrada");
						continue;
					}
				}

				// Tipo de conta
				if (opcaoConta == 1) {
					new ContaBO(cliente, TipoConta.CORRENTE);
				} else {
					new ContaBO(cliente, TipoConta.POUPANCA);
				}
			}

			// Transferencia entre contas
			else if (opcaoEscolhida == 2) {
				System.out.print("Conta que recebe: ");
				String numeroContaRecebe = scanner.next();
				// Procura conta no BD
				Conta contaRecebe = BancoDeDados.buscaContaPorNumero(numeroContaRecebe);

				if (contaRecebe == null) {
					continue;
				}

				System.out.print("Conta que paga: ");
				String numeroContaPaga = scanner.next();
				// Procura conta no BD
				Conta contaPaga = BancoDeDados.buscaContaPorNumero(numeroContaPaga);

				if (contaPaga == null) {
					continue;
				}

				System.out.print("Valor da transferência: ");
				double valor = scanner.nextDouble();
				ContaBO contaBO = new ContaBO(contaPaga);
				// Efetua transferência entre contas
				contaBO.transferirDeContaParaConta(contaRecebe, valor);
			}

			// Depósito em conta
			else if (opcaoEscolhida == 3) {

				System.out.print("Conta: ");
				String numeroConta = scanner.next();

				// Busca conta no BD
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				System.out.println("Digite o valor: ");
				double valor = scanner.nextDouble();

				ContaBO contaBO = new ContaBO(conta);
				// + valor na conta selecionada
				contaBO.depositar(valor);
			}

			// Saldo da conta
			else if (opcaoEscolhida == 4) {
				System.out.print("Conta: ");
				String numeroConta = scanner.next();
				
				// Busca conta no BD
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				ContaBO contaBO = new ContaBO(conta);
				// Retorna saldo
				contaBO.consultarSaldo();
			}

			// Taxas
			else if (opcaoEscolhida == 5) {
				List<Conta> lConta = BancoDeDados.buscarTodasAsContas();

				for (Conta conta : lConta) {
					ContaBO contaBO = new ContaBO(conta);
					contaBO.debitoCredito();
				}
			}

			// PIX
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

				// Cadastrar PIX
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
			
			// Transferencia PIX
			else if (opcaoEscolhida == 7) {
				System.out.print("Conta pagante: ");
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
			
			// Cadastrar CC CD
			else if (opcaoEscolhida == 8) {
				System.out.print("Número da conta: ");
				String numeroConta = scanner.next();
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);
				
				if (conta == null) {
					continue;
				}
				
				// Verifica TipoCliente e aumenta o Limite disponivel para CC e CD
				TipoCliente tc = conta.getCliente().getTipoCliente();
				double limite = 0;
				if (tc == TipoCliente.COMUM) {
					limite = 200;
					System.out.print("Seu limite é de: " + limite);
				}
				else if (tc == TipoCliente.PREMIUM) {
					limite = 1000;
					System.out.print("Seu limite é de: " + limite);
				}
				else if (tc == TipoCliente.SUPER) {
					limite = 5000;
					System.out.print("Seu limite é de: " + limite);
				}
				
				System.out.println("Qual tipo de cartão gostaria de solicitar? ");
				System.out.print("1 - Cartão Débito - 1");
				System.out.print("2 - Cartão Crédito - 2");
				System.out.println("3 - Multi-Cartão - 3");
				int opcaoCartão = scanner.nextInt();
				
				if (opcaoCartão == 1) {
					String numero = UUID.randomUUID().toString();
					System.out.print("Bandeira\n1 - Visa\n 2- MasterCard");
					int tipoBandeira = scanner.nextInt();
					Bandeira bandeiraCD = null;
					
					if (tipoBandeira == 1) {
						bandeiraCD = Bandeira.VISA;
					}
					else if
						(tipoBandeira == 2) {
							bandeiraCD = Bandeira.MASTERCARD;
					}
					System.out.print("Digite a senha: ");
					String senha = scanner.next();
					
					System.out.print("Seu cartão está ativo.\nSeu limite é: " + limite);
					
					CartaoDebito cd = new CartaoDebito(numero, bandeira, senha, true, limite);
				}
			}
			
			// Efetuar compra
			else if (opcaoEscolhida == 9) {
				
				menu.menuCompra();
				int opcaoCompra = scanner.nextInt();
				
				System.out.print("Quanto deseja gastar: ");
				double valorCompra = scanner.nextDouble();
				
				System.out.print("Confirma compra no valor de R$ %.2f?\nS/N: ");
				String confirmaCompra = scanner.next();
				
				if (confirmaCompra == "S") {
					
				}
			}
			// Saida
			else if (opcaoEscolhida == 0) {
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

		System.out.print("N�mero: ");
		int numero = scanner.nextInt();

		System.out.print("CEP: ");
		String cep = scanner.next();

		System.out.print("Bairro: ");
		String bairro = scanner.next();

		System.out.print("Cidade: ");
		String cidade = scanner.next();

		System.out.print("Estado: ");
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

			System.out.print("CPF: ");
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