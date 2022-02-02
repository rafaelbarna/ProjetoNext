package br.com.next.utils;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import br.com.next.bean.Bandeira;
import br.com.next.bean.Cartao;
import br.com.next.bean.CartaoCredito;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.Endereco;
import br.com.next.bean.Pix;
import br.com.next.bean.Seguro;
import br.com.next.bean.TipoChavePix;
import br.com.next.bean.TipoCliente;
import br.com.next.bean.TipoConta;
import br.com.next.bean.TipoSeguro;
import br.com.next.bo.ApoliceBO;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;

public class Main {

	private static Bandeira bandeira;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu();

		List<Seguro> seguro = new ArrayList<Seguro>();
//		seguro.setId("1");
//		seguro.setNome("Morte");
//		seguro.setRegras("Após a morte do Segurado");
//		
//		Seguro seguro1 = new Seguro();
//		seguro.setId("2");
//		seguro.setNome("Roubo");
//		seguro.setRegras("Quando furtado");
//
//		seguro.add(seguro);

		int opcaoEscolhida = -1;

		while (opcaoEscolhida != 0) {

			menu.exibirMenu();
			opcaoEscolhida = scanner.nextInt();

			// Cadastrar novo Cliente.
			if (opcaoEscolhida == 1) {
				Cliente cliente = Main.cadastrarCliente(scanner);
				if (cliente == null) {
					continue;
				}

				// Cadastra Conta depois de Cliente.
				menu.exibirMenuConta();
				int opcaoConta = scanner.nextInt();

				String cpf = cliente.getCpf();

				List<Conta> lConta = BancoDeDados.buscarContaPorCliente(cpf);

				// Conta já cadastrada.
				for (Conta conta : lConta) {
					if (opcaoConta == conta.getTipoConta().getId()) {
						System.out.println("Uma conta já está cadastrada");
						continue;
					}
				}

				// Cadastro Cliente > Tipo de conta
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

				contaBO.consultarSaldo();
				System.out.println("Saldo em conta: " + conta.getSaldo());
			}

			// Saldo da conta
			else if (opcaoEscolhida == 4) {
				System.out.print("Conta: ");
				String numeroConta = scanner.next();

				// Busca conta no BD
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					System.out.println("Conta não encontrada");
					continue;
				}

				ContaBO contaBO = new ContaBO(conta);
				contaBO.consultarSaldo();
			}

			// Cadastrar PIX
			else if (opcaoEscolhida == 5) {
				System.out.print("Conta a ser cadastrado PIX: ");
				String numeroConta = scanner.next();
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				menu.exibirMenuPix();

				int tipoPix = scanner.nextInt();
				String chavePix = "";
				TipoChavePix tcp = null;

				// PIX chave Aleatória
				if (tipoPix == 4) {
					chavePix = UUID.randomUUID().toString();
					tcp = TipoChavePix.ALEATORIO;

				} else {
					System.out.println("Informe a chave: ");
					chavePix = scanner.next();
				}

				// Outros TiposPix
				Pix pix = new Pix();
				pix.setAtivo(true);
				pix.setChave(chavePix);

				if (tipoPix == 1) {
					tcp = TipoChavePix.CPF;
				} else if (tipoPix == 2) {
					tcp = TipoChavePix.EMAIL;
				} else if (tipoPix == 3) {
					tcp = TipoChavePix.TELEFONE;
				}

				pix.setTipoChavePix(tcp);

				ContaBO contaBO = new ContaBO(conta);
				contaBO.adicionarPix(pix);
			}

			// Transferencia PIX
			else if (opcaoEscolhida == 6) {
				System.out.print("Conta pagante: ");
				String numeroConta = scanner.next();

				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					continue;
				}

				System.out.print("Informe a chave PIX da conta a receber: ");
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

			// Cadastrar Cartão
			else if (opcaoEscolhida == 7) {
				System.out.print("Número da conta: ");
				String numeroConta = scanner.next();
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				if (conta == null) {
					System.out.println("É necessário ter uma conta cadastrada para solicitar Cartões");
					continue;
				}

				// TipoCliente
				TipoCliente tc = conta.getCliente().getTipoCliente();
				double limite = 0;
				if (tc == TipoCliente.COMUM) {
					limite = 200;
				} else if (tc == TipoCliente.PREMIUM) {
					limite = 1000;
				} else if (tc == TipoCliente.SUPER) {
					limite = 5000;
				}

				System.out.println("Limite: " + limite);

				// Seleciona entre CD ou CC.
				System.out.println("Qual tipo de cartão gostaria de solicitar? ");
				System.out.println("1 - Cartão Débito - 1");
				System.out.println("2 - Cartão Crédito - 2");
				int opcaoCartao = scanner.nextInt();

				// Cartão Débito
				if (opcaoCartao == 1) {
					String numero = UUID.randomUUID().toString();
					System.out.print("Bandeira\n1 - Visa\n2 - MasterCard\n3 - Elo");
					int tipoBandeira = scanner.nextInt();
					Bandeira bandeiraCD;

					if (tipoBandeira == 1) {
						bandeiraCD = Bandeira.VISA;
					} else if (tipoBandeira == 2) {
						bandeiraCD = Bandeira.MASTERCARD;
					} else {
						bandeiraCD = Bandeira.ELO;
					}
					System.out.print("Digite a senha: ");
					String senha = scanner.next();

					System.out.println("Seu cartão está ativo.\nSeu limite é: " + limite);
					System.out.println("Número cartão: " + numero);

					// Adiciona CD a Conta do Cliente, e atualiza Lista de CD do Cliente.
					CartaoDebito cartao = new CartaoDebito(numero, bandeira, senha, true, limite);
					ContaBO contaBO = new ContaBO(conta);
					contaBO.adicionaCartao(cartao);
				}

				// Cartão Crédito.
				else if (opcaoCartao == 2) {
					String numero = UUID.randomUUID().toString();
					System.out.print("Bandeira\n1 - Visa\n2 - MasterCard\n3 - Elo");
					int tipoBandeira = scanner.nextInt();
					Bandeira bandeiraCD = null;

					if (tipoBandeira == 1) {
						bandeiraCD = Bandeira.VISA;
					} else if (tipoBandeira == 2) {
						bandeiraCD = Bandeira.MASTERCARD;
					} else {
						bandeiraCD = Bandeira.ELO;
					}

					System.out.print("Digite a senha: ");
					String senha = scanner.next();

					System.out.print("Seu cartão está ativo.\nSeu limite é: " + limite);
					System.out.println("\nNúmero cartão: " + numero);

					// Adiciona CC a Conta do Cliente, e atualiza Lista de CC do Cliente.
					CartaoCredito cartao = new CartaoCredito(numero, bandeira, senha, true, limite);
					ContaBO contaBO = new ContaBO(conta);
					contaBO.adicionaCartao(cartao);
				}
			}

			// Serviços para Cartão - Status e Bloqueio.
//			else if (opcaoEscolhida == 8) {
//				menu.menuCartao();
//				int opcaoCartao = scanner.nextInt();
//
//				// Status Cartão Débito
//				if (opcaoCartao == 1) {
//
//					System.out.print("Cartão: ");
//					String numeroCartao = scanner.next();
//					CartaoDebito cartaoDebito = BancoDeDados.(numeroCartao);
//
//					System.out.println("Número do cartão: " + cartaoDebito.getNumero() + "\nBandeira: "
//							+ cartaoDebito.getBandeira() + "\nSenha: " + cartaoDebito.getSenha() + "\nStatus: "
//							+ cartaoDebito.isAtivo() + "\nLimite: " + cartaoDebito.getLimiteDebito());
//				}
//
//				// Status Cartão Crédito
//				if (opcaoCartao == 2) {
//
//					System.out.print("Cartão: ");
//					String numeroCartao = scanner.next();
//					CartaoCredito cartaoCredito = BancoDeDados.buscaCartaoCredito(numeroCartao);
//
//					System.out.println("Número do cartão: " + cartaoCredito.getNumero() + "Bandeira: "
//							+ cartaoCredito.getBandeira() + "\nSenha: " + cartaoCredito.getSenha() + "\nStatus: "
//							+ cartaoCredito.isAtivo() + "\nVencimento: " + cartaoCredito.getDataVencimento()
//							+ "\nLimite: " + cartaoCredito.getLimite());
//				}
//
//				// Bloqueio de Cartão
//				else if (opcaoCartao == 3) {
//
//					// Atribui False ao Cartao
//					String valorNumero = scanner.next();
//					CartaoDebito cartaoDebito = BancoDeDados.buscaCartaoDebito(valorNumero);
//					cartaoDebito.setAtivo(false);
//					CartaoCredito cartaoCredito = BancoDeDados.buscaCartaoCredito(valorNumero);
//					cartaoCredito.setAtivo(false);
//					continue;
//				}
//			}

			// Compra Crédito
			else if (opcaoEscolhida == 9) {

				System.out.print("Conta: ");
				String numeroConta = scanner.next();
				Conta conta = BancoDeDados.buscaContaPorNumero(numeroConta);

				List<Cartao> lCartao = conta.getCartao();
				System.out.print("Cartão: ");
				String numeroCartao = scanner.next();
				Cartao cartao = BancoDeDados.buscaContaNumeroCartao(numeroCartao);

				if (conta == null || cartao == null || lCartao != null) {
					System.out.println("Não foram encontrados Conta ou Cartão com os dados");
					continue;
				}

				if(cartao.isAtivo()) {
					System.out.print("Quanto deseja gastar: ");
					double valor = scanner.nextDouble();
					
					System.out.println("Senha:");
					String senha = scanner.next();
					
					ContaBO contaBO = new ContaBO(conta);
					if(senha.equals(cartao.getSenha())) {
						contaBO.compraCartaoCredito(cartao, valor);
					}
					else {
						System.out.println("Senha incorreta");
					}
				}
				else {
					System.out.println("Cartão Bloqueado");
				}

				ContaBO contaBO = new ContaBO(conta);
				contaBO.exibirFatura(cartao);
			}

			// Compra Débito
			else if (opcaoEscolhida == 10) {
				
				Conta conta = null;
				
				System.out.print("Conta: ");
				String numeroConta = scanner.next();
				conta = BancoDeDados.buscaContaPorNumero(numeroConta);
				
				System.out.print("Cartão: ");
				String numeroCartao = scanner.next();
				
				conta = BancoDeDados.buscaContaNumeroCartao(numeroCartao);

				if (conta == null) {
					System.out.println("Não foram encontrados Conta ou Cartão com os dados");
					continue;
				}
				
				
				if(cartao.isAtivo()) {
					System.out.print("Quanto deseja gastar: ");
					double valor = scanner.nextDouble();
					
					System.out.println("Senha:");
					String senha = scanner.next();
					
					ContaBO contaBO = new ContaBO(conta);
					if(senha.equals(cartao.getSenha())) {
						contaBO.compraCartaoDebito(cartao, valor);
					}
					else {
						System.out.println("Senha incorreta");
					}
				}
				else {
					System.out.println("Cartão Bloqueado");
				}

			}

			else if (opcaoEscolhida == 15) {

				menu.menuSeguro();
				int opcaoSeguro = scanner.nextInt();

				if (opcaoSeguro == 1) {
					menu.sobreSeguro();
				}

//				else if (opcaoSeguro == 2) {
//					System.out.print("Cartão de Crédito: ");
//					String numeroCartao = scanner.next();
//					CartaoCredito cartao = BancoDeDados.buscaCartaoCredito(numeroCartao);
//
//					if (cartao == null) {
//						System.out.println("Nenhum Cartão de Crédito cadastrado em Conta");
//						continue;
//					}
//
//					System.out.println("Qual Seguro deseja contratar:\n" + "1 - Seguro Morte\n"
//							+ "2 - Seguro Invalidez\n" + "3 - Seguro Desemprego");
//					int escolhaSeguro = scanner.nextInt();
//					TipoSeguro ts = null;
//
//					// TipoSeguro
//					if (escolhaSeguro == 1) {
//						ts = TipoSeguro.MORTE;
//					} else if (escolhaSeguro == 2) {
//						ts = TipoSeguro.INVALIDEZ;
//					} else if (escolhaSeguro == 3) {
//						ts = TipoSeguro.DESEMPREGO;
//					}
//
//					// Vigência e Cálculo
//					System.out.println("Quantos Mêses de Seguro:");
//					int contrataMesSeguro = scanner.nextInt();
//
//					// Boolean finalizar contratação do Seguro
//					System.out.println("Esta de acordo com as regras:\n1 - Sim\n2 - Não");
//					int escolhaRegra = scanner.nextInt();
//
//					if (escolhaRegra == 1) {
//						int id = Utils.numeroRandomico(1, 10);
//						
//						ApoliceBO apoliceBO = new ApoliceBO();
//						apoliceBO.adicionaSeguro(cartao, id, ts, contrataMesSeguro, true);
//						System.out.println("Seguro contratado");
//						System.out.println("ID: " + id);
//					} else {
//						continue;
//					}
//				} 
//				
//				else if (opcaoSeguro == 3) {
//					System.out.println("ID da Apólice: ");
//					int idApolice = scanner.nextInt();
//					ApoliceBO apoliceBO = new ApoliceBO();
//					apoliceBO.dadoSeguro();
//				}
			}

			// Sair da Aplicação
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
		System.out.print("Endereço: ");
		String logradouro = scanner.nextLine();

		System.out.print("Número: ");
		int numero = scanner.nextInt();

		System.out.print("Bairro: ");
		String bairro = scanner.next();

		System.out.print("Cidade: ");
		String cidade = scanner.next();

		System.out.print("Estado: ");
		String estado = scanner.next();

		System.out.print("CEP: ");
		String cep = scanner.next();

		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setCep(cep);

		scanner.nextLine();
		System.out.print("Digite o nome do cliente: ");
		String nome = scanner.nextLine();

		String cpf;

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