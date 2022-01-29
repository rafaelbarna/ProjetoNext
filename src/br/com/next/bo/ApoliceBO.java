package br.com.next.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import java.sql.Date;

import br.com.next.bean.Apolice;
import br.com.next.bean.Cartao;
import br.com.next.bean.CartaoCredito;
import br.com.next.bean.Conta;
import br.com.next.bean.Seguro;
import br.com.next.bean.TipoSeguro;

public class ApoliceBO {

	private Apolice Apolice;

	private Date adiciona15dias() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		Date add15Dias = calendar.getTime();

		return add15Dias;
	}

	private Date adiciona1ano() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		Date add1Ano = calendar.getTime();

		return add1Ano;
	}

	public Apolice adicionaSeguro(CartaoCredito cartao, int id, TipoSeguro ts, int contrataMesSeguro, boolean escolhaRegra) {
		Apolice apolice = new Apolice();
		apolice.setId(id);
		apolice.setTipoSeguro(ts);
		apolice.setEscolhaRegra(escolhaRegra);
//		apolice.getCartaoCredito().getApolice().setSeguro(new Seguro(cartao, id, ts, contrataMesSeguro, escolhaRegra));
//		apolice.getCartaoCredito().getApolice().setDataAssinatura(new Date());
//		apolice.getCartaoCredito().getApolice().setDataCarencia(this.adiciona15dias());
		return apolice;
	}

	public void dadosApolice(CartaoCredito cartaoCredito) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAssinatura = sdf.format(cartaoCredito.getApolice().getDataAssinatura());
		String dataCarencia = sdf.format(cartaoCredito.getApolice().getDataCarencia());
		System.out.println("Conta: " + cartaoCredito.getNumero() + "\nData Assinatura: " + dataAssinatura
				+ "\nData Carência: " + dataCarencia);
	}

	public void dadoSeguro() {
		Apolice apolice = new Apolice();
		apolice.getId();
		apolice.getDataAssinatura();
		apolice.getDataCarencia();
		apolice.getDataValidade();
		System.out.println("ID: " + apolice.getId() + "\nData Assinatura: " + apolice.getDataAssinatura() + "\nData Carencia: " + apolice.getDataCarencia() + "\nData Validade: " + apolice.getDataValidade());
	}

	public boolean validaCarencia(Conta conta, double valor) {
		return false;
	}
}