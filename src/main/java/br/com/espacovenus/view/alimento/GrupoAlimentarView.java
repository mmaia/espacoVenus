package br.com.espacovenus.view.alimento;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.espacovenus.model.alimento.GrupoAlimentar;
import br.com.espacovenus.service.alimento.GrupoAlimentarService;

@Named
@RequestScoped
public class GrupoAlimentarView {

	Logger logger = Logger.getLogger(GrupoAlimentarView.class.getName());
	
	private GrupoAlimentar grupoAlimentar;

	@EJB
	private GrupoAlimentarService grupoAlimentarService;
	
	@PostConstruct
    public void init() {
		grupoAlimentar = new GrupoAlimentar();
	}
	
	public void cadastrarGrupoAlimentar()
	{
		try{
		grupoAlimentarService.create(grupoAlimentar);
		logger.info("Grupo Alimentar gravado com sucesso: " + grupoAlimentar.getNomeGrupo());
		}
		catch(RuntimeException re)
		{
			re.printStackTrace();
			logger.severe("Erro ao cadastrar grupo alimentar: " + re.getMessage());
		}
		grupoAlimentar = null;
	}
	
	public void excluiGrupoAlimentar()
	{
		try{
		grupoAlimentarService.delete(grupoAlimentar);
		logger.info("Grupo Alimentar excluido: " + grupoAlimentar.getNomeGrupo());
		}
		catch(RuntimeException re)
		{
			re.printStackTrace();
			logger.severe("Erro ao excluir grupo: " + re.getMessage());
		}
	}
	
	@PreDestroy
	public void limpar()
	{
		grupoAlimentar = null;
	}
	
	public GrupoAlimentar getGrupoAlimentar() {
		return grupoAlimentar;
	}
}
