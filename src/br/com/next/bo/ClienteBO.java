package br.com.next.bo;

import java.util.Date;

import br.com.next.bean.Cliente;

import br.com.next.bean.Endereco;

import br.com.next.bean.TipoCliente;

public class ClienteBO {

	public ClienteBO() {

	}

	public Cliente cadastrarCliente(String nome, String cpf, Date dataNascimento, Endereco endereco) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTipoCliente(TipoCliente.COMUM);
		cliente.setDataNascimento(dataNascimento);
		cliente.setEndereco(endereco);
		return cliente;
	}

	public boolean validarCpf(String cpf) {
		try {
			Long.parseLong(cpf);

			if (cpf.length() == 1) {
				return true;
			}
			System.out.println("CPF inválido");
			return false;
		}

		catch (Exception e) {
			System.out.println("CPF inválido");
			return false;
		}
	}
}
