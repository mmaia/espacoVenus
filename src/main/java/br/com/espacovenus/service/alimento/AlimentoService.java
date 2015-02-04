package br.com.espacovenus.service.alimento;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.espacovenus.model.alimento.Alimento;

@Stateless
public class AlimentoService {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public Long create(Alimento alimento)
	{
		em.persist(alimento);
		return alimento.getId();
	}
	
	@Produces
    @Named("AlimentosList")
    public List<Alimento> list() {
        return em.createNamedQuery("Alimento.list", Alimento.class).getResultList();
    }
}
