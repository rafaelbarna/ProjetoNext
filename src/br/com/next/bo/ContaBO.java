package br.com.next.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.next.bean.Cliente;

import br.com.next.bean.Conta;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoConta;
import br.com.next.utils.BancoDeDados;

public class ContaBO {

	private Conta conta;

	public ContaBO (Conta conta) {
		this.conta = conta;
	}

	public ContaBO(Cliente cliente, TipoConta tipoConta) {
		this.conta = this.gerarConta(cliente, tipoConta);
	}

	private Conta gerarConta(Cliente cliente, TipoConta tipoConta) {
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setNumero((UUID.randomUUID().toString()));
		conta.setSaldo(0);
		conta.setTipoConta(tipoConta);
		
		Date data = this.getDateAdd1Month();
		conta.setData(data);

		BancoDeDados.insereConta(conta.getNumero(), conta);

		System.out.println(conta.getNumero());

		return conta;
	}
	
	public Date getDateAdd1Month() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date data = cal.getTime();
		
		return data;
	}

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

	public boolean transferirDeContaParaConta(Conta contaRecebe, double valor) {
		if (this.conta.getSaldo() >= valor) {
			
			if (this.conta.getTipoConta() == TipoConta.CORRENTE && this.conta.getTipoConta().getId() != contaRecebe.getTipoConta().getId()) {
				
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
			this.conta.setSaldo(meuSaldo);

			double saldoOutraConta = contaRecebe.getSaldo();
			saldoOutraConta += valor;
			contaRecebe.setSaldo(saldoOutraConta);

			BancoDeDados.insereConta(contaRecebe.getNumero(), contaRecebe);
			BancoDeDados.insereConta(this.conta.getNumero(), this.conta);

			System.out.println("Transferência efetuada");

			return true;
		}

		System.out.println("Saldo insuficiente");
		return false;
	}

	public void depositar(double valor) {
		double meuSaldo = this.conta.getSaldo();
		meuSaldo += valor;
		this.conta.setSaldo(meuSaldo);

		BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
	}

	public void consultarSaldo() {

		String nome = this.conta.getCliente().getNome();
		String cpf = this.conta.getCliente().getCpf();
		double valor = this.conta.getSaldo();

		System.out.println("Nome: " + nome + "\nCPF: " + cpf + "\nSaldo em conta: " + valor);
	}
	
	public void adicionarPix(Pix pix) {
		this.conta.setPix(pix);
		
		BancoDeDados.insereConta(this.conta.getNumero(), this.conta);
		
	}
}
