package br.com.espacovenus.view.alimento;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.espacovenus.model.alimento.Alimento;
import br.com.espacovenus.service.alimento.AlimentoService;

@Named
@RequestScoped
public class AlimentoView {
	Logger log = Logger.getLogger(AlimentoView.class.getName());
	
	
	private Alimento alimento;
	
	@EJB
	private AlimentoService alimentoService;
	
	@PostConstruct
    public void init() {
		alimento = new Alimento();
	}
	
	public void cadastrarAlimento()
	{
		log.info("Entering AlimentoView.cadastrarAlimento");
		try{
		alimentoService.create(alimento);
		}
		catch(RuntimeException re)
		{
			re.printStackTrace();
			log.severe("Erro ao cadastrar alimento: " + re.getMessage());
		}
		alimento = null;
	}
	
	@PreDestroy
	public void limpar()
	{
		alimento = null;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	
	
}
