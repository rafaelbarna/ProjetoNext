package br.com.next.bo;

import br.com.next.bean.Seguro;
import br.com.next.bean.TipoSeguro;

public class SeguroBO {

	public SeguroBO(TipoSeguro ts, boolean b) {
		Seguro seguro = new Seguro();
		seguro.setTipoSeguro(ts);
		seguro.setRegras(true);
	}
}
