package br.com.espacovenus.service.alimento;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.espacovenus.model.alimento.GrupoAlimentar;

@Stateless
public class GrupoAlimentarService {
	
	Logger log = Logger.getLogger(GrupoAlimentarService.class.getName());
	
	@PersistenceContext
	private EntityManager em;

	public GrupoAlimentar find(Long id) {
		return em.find(GrupoAlimentar.class, id);
	}

	public GrupoAlimentar find(String nomeGrupo) {
		List<GrupoAlimentar> grupoAlimentar = em
				.createNamedQuery("GrupoAlimentar.findByNomeGrupo",
						GrupoAlimentar.class)
				.setParameter("nomeGrupo", nomeGrupo).getResultList();
		return grupoAlimentar.isEmpty() ? null : grupoAlimentar.get(0);
	}
	
	@Produces
    @Named("gruposAlimentaresList")
    public List<GrupoAlimentar> list() {
		log.info("Executing GrupoAlimentarService.list");
        List<GrupoAlimentar> gruposAlimentares =  em.createNamedQuery("GrupoAlimentar.list", GrupoAlimentar.class).getResultList();
        log.info("Number of grupos alimentares:  " + gruposAlimentares.size());
        return gruposAlimentares;
    }

    public Long create(GrupoAlimentar grupoAlimentar) {
        em.persist(grupoAlimentar);
        return grupoAlimentar.getId();
    }

    public void update(GrupoAlimentar grupoAlimentar) {
        em.merge(grupoAlimentar);
    }

    public void delete(GrupoAlimentar grupoAlimentar) {
        em.remove(em.contains(grupoAlimentar) ? grupoAlimentar : em.merge(grupoAlimentar));
    }

}
