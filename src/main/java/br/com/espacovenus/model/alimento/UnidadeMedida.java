package br.com.espacovenus.model.alimento;

public enum UnidadeMedida {
	
	//liquidos
	ML("ml"), LITRO("L"), COPO("Copo"), XICARA("Xic"), XICARA_PEQUENA("Xic. Peq."), XICARA_GRANDE("Xic. Gde."),
	
	//solidos
	GRAMAS("g"), KG("Kg"), FATIA("fatia"), COLHER_SOPA("colh. sopa"), COLHER_SOBREMESA("colh. sobrem."), COLHER_CHA("colh. chá");
	
	/**
	 * Representação literal discursiva da unidade de medida i.e - ml, L, Xic
	 */
	private final String representacao;
	
	UnidadeMedida(String representacao)
	{
		this.representacao = representacao;
	}
}
