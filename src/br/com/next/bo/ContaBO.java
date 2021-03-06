package br.com.next.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.next.bean.Cartao;
import br.com.next.bean.CartaoCredito;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Compra;
import br.com.next.bean.Conta;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoCliente;
import br.com.next.bean.TipoConta;
import br.com.next.utils.BancoDeDados;

public class ContaBO {

	private Conta conta;

	public ContaBO(Conta conta) {
		this.conta = conta;
	}

	public ContaBO(Cliente cliente, TipoConta tipoConta) {
		this.conta = this.gerarConta(cliente, tipoConta);
	}

	// Cria nova conta, com dados do Main (1). Cliente seleciona o tipo de Conta que deseja.
	private Conta gerarConta(Cliente cliente, TipoConta tipoConta) {
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setNumero((UUID.randomUUID().toString()));
		conta.setSaldo(0);
		conta.setTipoConta(tipoConta);

		Date data = this.getDateAdd1Month();
		conta.setData(data);

		BancoDeDados.insereConta(conta.getNumero(), conta);

		System.out.println("Número da Conta: " + conta.getNumero());

		return conta;
	}

	// Adiciona UM mês no DATA.
	public Date getDateAdd1Month() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date data = cal.getTime();

		return data;
	}

	// Taxas para Conta Corrente e Conta Poupança.
	public void debitoCredito() {
		if (this.conta.getData().before(new Date())) {
			if (this.conta.getTipoConta() == TipoConta.CORRENTE) {
				double valor = this.conta.getSaldo();
				valor -= valor * 0.45;
				this.conta.setSaldo(valor);
				BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
			} else {
				double valor = this.conta.getSaldo();
				valor = valor * 0.03;
				this.depositar(valor);
			}

			Date data = this.getDateAdd1Month();
			this.conta.setData(data);
			BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
		}
	}

	// Transfere Valor para outra Conta. Caso Saldo for menor que Valor não permitir
	// transação.
	public boolean transferirDeContaParaConta(Conta contaRecebe, double valor) {
		if (this.conta.getSaldo() >= valor) {

			if (this.conta.getTipoConta() == TipoConta.CORRENTE
					&& this.conta.getTipoConta().getId() != contaRecebe.getTipoConta().getId()) {

				double valorSaldo = this.conta.getSaldo();

				if (valor - 5.60 <= valor) {
					System.out.println("Saldo insuficiente");
					return false;
				}

				valorSaldo -= 5.60;
				this.conta.setSaldo(valorSaldo);
			}

			double meuSaldo = this.conta.getSaldo();
			meuSaldo -= valor;
			// Atribui novo valor ao Saldo da conta.
			this.conta.setSaldo(meuSaldo);

			double saldoOutraConta = contaRecebe.getSaldo();
			saldoOutraConta += valor;
			contaRecebe.setSaldo(saldoOutraConta);

			// Atualiza ContaRecebe com Valor da Conta em uso. Atualiza Conta em uso com
			// novo saldo.
			BancoDeDados.insereConta(contaRecebe.getNumero(), contaRecebe);
			BancoDeDados.insereConta(this.conta.getNumero(), this.conta);

			System.out.println("Transferência concluida");

			return true;
		}

		System.out.println("Saldo insuficiente");
		return false;
	}

	// Deposita em conta Valor na Conta do Cliente, e atualiza Saldo da Conta com novo Valor.
	public void depositar(double valor) {
		double saldoConta = this.conta.getSaldo();
		saldoConta += valor;
		this.conta.setSaldo(saldoConta);
		
		if(saldoConta <= 5000){
			this.conta.getCliente().setTipoCliente(TipoCliente.COMUM);
		}else if(saldoConta > 5000 && saldoConta <= 10000) {
			this.conta.getCliente().setTipoCliente(TipoCliente.PREMIUM);
		}else {
			this.conta.getCliente().setTipoCliente(TipoCliente.SUPER);
		}
		
		BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
	}

	// Retorna ao cliente infos de Nome, CPF, e Saldo.
	public void consultarSaldo() {
		String nome = this.conta.getCliente().getNome();
		String cpf = this.conta.getCliente().getCpf();
		double valor = this.conta.getSaldo();
		System.out.println("Saldo: " + valor);
	}

	// Adiciona função PIX a conta, e atualiza Conta com nova a função.
	public void adicionarPix(Pix pix) {
		this.conta.setPix(pix);
		BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
		System.out.println("PIX Ativado");
	}

	// Atribui o Cartão criado a Conta do Cliente, e atualiza Conta com novo Cartão.
	public void adicionaCartao(Cartao cartao) {
		this.conta.addCartao(cartao);
		BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
	}

	// Compra com Cartao Débito.
	public void compraCartaoDebito(Cartao cartao, double valor) {
		CartaoDebito cartaoDebito = (CartaoDebito) cartao;
		double saldoConta = this.conta.getSaldo();

		// Compra efetuada. Atualiza Saldo - Valor para o Saldo em Conta.
		if (cartaoDebito.getLimiteDebito() < valor || saldoConta > valor) {
			saldoConta = saldoConta - valor;
			this.conta.setSaldo(saldoConta);
			BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
			System.out.println("Compra Efetuada");
		} else {
			System.out.println("Saldo insuficiente");
		}
	}
	
	public void compraCartaoCredito(Cartao cartao, double valor) {
		CartaoCredito cartaoCredito = (CartaoCredito) cartao;
		
		if(cartaoCredito.getLimite() >= valor) {
			Compra compra = new Compra(new Date(), valor);
			cartaoCredito.addCompras(compra);
			double limite = cartaoCredito.getLimite();
			limite -= valor;
			cartaoCredito.setLimite(limite);
			
			double valorFatura = cartaoCredito.getValorFatura();
			valorFatura += valor;
			cartaoCredito.setValorFatura(valorFatura);
			
			this.conta.alteraCartaoCredito(cartaoCredito);
			
			
			BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
		}
		else {
			System.out.println("Recusado");
		}
	}

	public void exibirFatura(Cartao cartao) {
		CartaoCredito cartaoCredito = (CartaoCredito) cartao;
		List<Compra> lCompra = cartaoCredito.getCompras();
		System.out.println("Limite: " + cartaoCredito.getLimite());
		System.out.println("Fatura: " + cartaoCredito.getValorFatura());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(cartaoCredito.getDataVencimento());
		System.out.println("Vencimento: " + data);
		SimpleDateFormat sdfHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for(Compra compra : lCompra) {
			String dataCompra = sdfHora.format(compra.getCompra());
			double valor = compra.getValor();
			System.out.println("Dia: " + dataCompra + " - Valor: " + valor);
		}
	}
}
